package com.example.project.Controllers;

import com.example.project.Entities.Departments;
import com.example.project.Models.DepartmentModel;
import com.example.project.Services.DepartmentsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentsServices departmentsServices;

    @PostMapping("/createDepartment")
    public ResponseEntity<Departments> createDepartment(@RequestBody DepartmentModel departmentModel) {
        try {
            Departments newDepartment = departmentsServices.createDepartment(departmentModel);

            return ResponseEntity.of(Optional.of(newDepartment));
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/updateDepartmentName/{deptId}")
    public ResponseEntity<Departments> updateDepartmentName(@PathVariable int deptId, @RequestParam("department_name") String name) {
        try {
           Departments updatedDepartment =  departmentsServices.updateDepartmentName(deptId, name);

           return ResponseEntity.of(Optional.of(updatedDepartment));
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/updateDepartmentCapacity/{deptId}")
    public ResponseEntity<Departments> updateDepartmentCapacity(@PathVariable int deptId, @RequestParam("department_capacity") int capacity) {
        try {
            Departments updatedDepartment =  departmentsServices.updateDepartmentCapacity(deptId, capacity);

            return ResponseEntity.of(Optional.of(updatedDepartment));
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

}
