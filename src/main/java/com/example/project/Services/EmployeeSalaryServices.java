package com.example.project.Services;

import com.example.project.Entities.EmployeeSalary;
import com.example.project.Entities.Employees;
import com.example.project.Models.EmployeeSalaryModel;
import com.example.project.Repositories.EmployeeSalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeSalaryServices {

    @Autowired
    private EmployeeSalaryRepository employeeSalaryRepository;

    @Autowired
    private EmployeesServices employeesServices;

    public EmployeeSalary createEmployeeSalary(EmployeeSalaryModel employeeSalaryModel) {
        EmployeeSalary newEmployeeSalary = new EmployeeSalary();
        newEmployeeSalary.setEmployee(employeesServices.fetchEmployeeByEmail(employeeSalaryModel.getEmployee()));
        newEmployeeSalary.setPaymentDate(employeeSalaryModel.getPaymentDate());
        newEmployeeSalary.setAmount(employeeSalaryModel.getAmount());
        newEmployeeSalary.setDescription(employeeSalaryModel.getDescription());

        return employeeSalaryRepository.save(newEmployeeSalary);
    }

    public EmployeeSalary fetchEmployeeSalary(Employees employee, int month, int year) {
        return employeeSalaryRepository.findEmployeeSalary(employee, month, year);
    }

    public List<EmployeeSalary> fetchAllByEmployeeSalary(Employees employee) {
        return employeeSalaryRepository.findAllByEmployee(employee);
    }


}
