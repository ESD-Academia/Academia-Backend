package com.example.project.Controllers;

import com.example.project.Entities.Employees;
import com.example.project.Models.EmployeeModel;
import com.example.project.Services.EmployeesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeesServices employeesServices;

    @PostMapping("/createEmployee")
    public ResponseEntity<Employees> createEmployee(@RequestBody EmployeeModel employeeModel) {
        try {
            Employees newEmployee = employeesServices.createEmployee(employeeModel);

            return ResponseEntity.of(Optional.of(newEmployee));
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/getEmployee")
    public ResponseEntity<Employees> getEmployee(@RequestParam("email") String email) {
        try {
            Employees reqEmployee = employeesServices.fetchEmployeeByEmail(email);

            return ResponseEntity.of(Optional.of(reqEmployee));
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
