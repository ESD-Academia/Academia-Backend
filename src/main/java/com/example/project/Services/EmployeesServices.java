package com.example.project.Services;

import com.example.project.Entities.Employees;
import com.example.project.Models.EmployeeModel;
import com.example.project.Repositories.DepartmentsRepository;
import com.example.project.Repositories.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeesServices {

    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private DepartmentsServices departmentsServices;

    @Autowired
    private LoginServices loginServices;


    public Employees createEmployee(EmployeeModel employeeModel) {
        Employees newEmployee = new Employees();
        newEmployee.setFirstName(employeeModel.getFirstName());
        newEmployee.setLastName(employeeModel.getLastName());
        newEmployee.setEmail(employeeModel.getEmail());
        newEmployee.setTitle(employeeModel.getTitle());
        newEmployee.setDepartment(departmentsServices.fetchDepartmentByName(employeeModel.getDepartment()));
        newEmployee.setLogin(loginServices.fetchLogin(employeeModel.getEmail()));

        return employeesRepository.save(newEmployee);
    }

    public Employees fetchEmployee(int empId) {
        return employeesRepository.findByEmpId(empId);
    }

    public Employees fetchEmployeeByEmail(String email) { return employeesRepository.findByEmail(email); }

//    public Employees updateEmployeePassword(int empId, String newPassword) {
//        Employees employeeToBeUpdated = fetchEmployee(empId);
//        employeeToBeUpdated.setPassword(newPassword);
//
//        return employeesRepository.save(employeeToBeUpdated);
//    }

}
