package com.example.project.Services;

import com.example.project.Entities.Departments;
import com.example.project.Models.DepartmentModel;
import com.example.project.Repositories.DepartmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentsServices {

    @Autowired
    private DepartmentsRepository departmentsRepository;

    public Departments createDepartment(DepartmentModel departmentModel) {
        Departments newDepartment = new Departments();
        newDepartment.setName(departmentModel.getName());
        newDepartment.setCapacity(departmentModel.getCapacity());

        return departmentsRepository.save(newDepartment);
    }

    public Departments fetchDepartment(int deptId) {
        return departmentsRepository.findByDepartmentId(deptId);
    }

    public Departments fetchDepartmentByName(String name) { return departmentsRepository.findByName(name); }

    public Departments updateDepartmentName(int deptId, String newName) {
        Departments departmentToBeUpdated = fetchDepartment(deptId);
        departmentToBeUpdated.setName(newName);

        return departmentsRepository.save(departmentToBeUpdated);
    }

    public Departments updateDepartmentCapacity(int deptId, int newCapacity) {
        Departments departmentToBeUpdated = fetchDepartment(deptId);
        departmentToBeUpdated.setCapacity(newCapacity);

        return departmentsRepository.save(departmentToBeUpdated);
    }

}
