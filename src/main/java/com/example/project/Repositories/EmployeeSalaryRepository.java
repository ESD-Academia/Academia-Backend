package com.example.project.Repositories;

import com.example.project.Entities.EmployeeSalary;
import com.example.project.Entities.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeSalaryRepository extends JpaRepository<EmployeeSalary, Integer> {

    @Query("SELECT es from EmployeeSalary es where es.employee =:reqEmployee and month(es.paymentDate)=:month and year(es.paymentDate)=:year")
    EmployeeSalary findEmployeeSalary(@Param("reqEmployee")Employees employee, @Param("month") int month, @Param("year") int year);

    List<EmployeeSalary> findAllByEmployee(Employees employee);

}
