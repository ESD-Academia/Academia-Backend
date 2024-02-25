package com.example.project.Models;

import com.example.project.Entities.Departments;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeModel {

    private String firstName;
    private String lastName;
    private String email;
    private String title;
    private String department;

}
