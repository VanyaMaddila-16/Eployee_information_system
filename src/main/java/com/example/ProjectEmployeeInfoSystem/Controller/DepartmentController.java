package com.example.ProjectEmployeeInfoSystem.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjectEmployeeInfoSystem.Entity.Department;
import com.example.ProjectEmployeeInfoSystem.Entity.Employee;
import com.example.ProjectEmployeeInfoSystem.Repository.DepartmentRepo;
import com.example.ProjectEmployeeInfoSystem.Repository.EmployeeRepo;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    DepartmentRepo departmentRepo;

    @Autowired
    EmployeeRepo employeeRepo;

    // Adding a new Department details
    public void addDepartment(Department department) {
        if (department != null) {
            departmentRepo.save(department);
        }

    }

    // Assign a HOD(Employee Id) in Department
    public void setDepartmentHod(int empId, int deptId) {
        Department department = departmentRepo.findById(deptId)
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));
        Employee hod = employeeRepo.findById(empId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        department.setHod(hod);
        departmentRepo.save(department);
    }

    // Get One Department details by Id
    //
    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getDepartmentById(@PathVariable int id) {
        try {
            Optional<Department> departmentOptional = departmentRepo.findById(id);
            if (departmentOptional.isPresent()) {
                Department department = departmentOptional.get();
                return ResponseEntity.ok().body(department);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Department not found"); // Department not
                                                                                                 // found
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while fetching the department.");
        }
    }

    // Get all Department details
    //
    public String getMethodName(@RequestParam String param) {
        return new String();
    }

    public ResponseEntity<?> getAllDepartments() {
        try {
            List<Department> departments = departmentRepo.findAll();
            return ResponseEntity.ok().body(departments);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while fetching departments.");
        }
    }
}