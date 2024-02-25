package com.example.project.Models;

import com.example.project.Entities.Employees;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeSalaryModel {

    private String employee;
    private Date paymentDate;
    private int amount;
    private String description;

}
