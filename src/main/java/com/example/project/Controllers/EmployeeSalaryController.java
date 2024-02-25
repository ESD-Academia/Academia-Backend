package com.example.project.Controllers;

import com.example.project.Entities.EmployeeSalary;
import com.example.project.Entities.Employees;
import com.example.project.Models.EmployeeSalaryModel;
import com.example.project.Services.CsvWriter;
import com.example.project.Services.EmployeeSalaryServices;
import com.example.project.Services.EmployeesServices;
import com.itextpdf.text.pdf.PdfPTable;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;


import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/employeeSalary")
public class EmployeeSalaryController {

    @Autowired
    private EmployeeSalaryServices employeeSalaryServices;

    @Autowired
    private EmployeesServices employeesServices;

    @PostMapping("/createEmployeeSalary")
    public ResponseEntity<EmployeeSalary> createEmployeeSalary(@RequestBody EmployeeSalaryModel employeeSalaryModel) {
        try {
            EmployeeSalary newEmployeeSalary = employeeSalaryServices.createEmployeeSalary(employeeSalaryModel);

            return ResponseEntity.of(Optional.of(newEmployeeSalary));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/getEmployeeSalary/{empId}/{year}/{month}")
    public ResponseEntity<EmployeeSalary> getEmployeeSalary(@PathVariable("empId") int empId, @PathVariable("year") int year, @PathVariable("month") int month) {
        try {
            Employees reqEmployee = employeesServices.fetchEmployee(empId);
            EmployeeSalary reqSalary = employeeSalaryServices.fetchEmployeeSalary(reqEmployee, month, year);

            return ResponseEntity.of(Optional.of(reqSalary));
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/getEmployeeSalary/{empId}")
    public ResponseEntity<List<EmployeeSalary>> getAllEmployeeSalary(@PathVariable("empId") int empId) {
        try {
            Employees reqEmployee = employeesServices.fetchEmployee(empId);

            List<EmployeeSalary> reqSalaries = employeeSalaryServices.fetchAllByEmployeeSalary(reqEmployee);

            return ResponseEntity.of(Optional.of(reqSalaries));
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/getEmployeeSalaryCSV/{empId}")
    public ResponseEntity<byte[]> getEmployeeSalaryCSV(@PathVariable("empId") int empId) throws IOException {
        try {
            Employees reqEmployee = employeesServices.fetchEmployee(empId);

            CsvWriter csvWriter = new CsvWriter();
            csvWriter.writeObjects("employeeSalaryHistory.csv", reqEmployee.getEmployeeSalaries());

            FileInputStream inputStream = new FileInputStream("employeeSalaryHistory.csv");
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            byte[] bytes = outputStream.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "data.csv");

            return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/getEmployeeSalaryPDF/{empId}/{year}/{month}")
    public ResponseEntity<byte[]> getEmployeeSalaryPDF(@PathVariable("empId") int empId,
                                                       @PathVariable("year") int year, @PathVariable("month") int month) {
        try {
            Employees reqEmployee = employeesServices.fetchEmployee(empId);
            EmployeeSalary reqSalary = employeeSalaryServices.fetchEmployeeSalary(reqEmployee, month, year);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            document.open();
            PdfPTable table = new PdfPTable(5);
            table.addCell("ID");
            table.addCell("Employee Name");
            table.addCell("Payment Date");
            table.addCell("Amount");
            table.addCell("Description");

            table.addCell(String.valueOf(reqSalary.getId()));
            table.addCell(reqSalary.getEmployee().getFirstName() + " " + reqSalary.getEmployee().getLastName());
            table.addCell(String.valueOf(reqSalary.getPaymentDate()));
            table.addCell(String.valueOf(reqSalary.getAmount()));
            table.addCell(reqSalary.getDescription());
            document.add(table);
            document.close();
            byte[] pdfBytes = outputStream.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "data.pdf");

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);



        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
