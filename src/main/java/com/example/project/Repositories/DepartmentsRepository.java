package com.example.project.Repositories;

import com.example.project.Entities.Departments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentsRepository extends JpaRepository<Departments, Integer> {
    Departments findByDepartmentId(int departmentId);
    Departments findByName(String name);
}
