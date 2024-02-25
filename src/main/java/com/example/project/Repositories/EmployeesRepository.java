package com.example.project.Repositories;

import com.example.project.Entities.Departments;
import com.example.project.Entities.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Integer> {
    Employees findByEmpId(int empId);
    Employees findByFirstName(String fName);
    Employees findByLastName(String lName);
    Employees findByEmail(String email);
    Employees findByDepartment(Departments department);

}
