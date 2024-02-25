package com.example.project.Services;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.project.Entities.EmployeeSalary;
import com.opencsv.CSVWriter;

public class CsvWriter {
    public void writeObjects(String filePath, List<EmployeeSalary> objects) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(filePath));
        writer.writeAll(objectsToCsv(objects));
        writer.close();
    }

    private List<String[]> objectsToCsv(List<EmployeeSalary> objects) {
        // Convert each object to a String array
        // representing a line of the file
        // and add it to the list
        List<String[]> csvData = new ArrayList<>();
        for (EmployeeSalary object : objects) {
            csvData.add(objectToStringArray(object));
        }
        return csvData;
    }

    private String[] objectToStringArray(EmployeeSalary object) {
        // Convert the object to a String array
        // representing a line of the file
        // and return it
        // You can customize this method to suit your needs

        return (object.getId() + ", " + object.getPaymentDate() + ", " + object.getAmount() + ", " + object.getDescription()).split(", ");
    }
}