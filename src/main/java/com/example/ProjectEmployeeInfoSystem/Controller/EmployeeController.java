package com.example.ProjectEmployeeInfoSystem.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjectEmployeeInfoSystem.Entity.Department;
import com.example.ProjectEmployeeInfoSystem.Entity.Employee;
import com.example.ProjectEmployeeInfoSystem.Entity.Job;
import com.example.ProjectEmployeeInfoSystem.Repository.DepartmentRepo;
import com.example.ProjectEmployeeInfoSystem.Repository.EmployeeRepo;
import com.example.ProjectEmployeeInfoSystem.Repository.JobRepo;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    JobRepo jobRepo;

    @Autowired
    DepartmentRepo departmentRepo;

    @Autowired
    JobHistoryController jobHistoryController;

    // Adding a new Employee details
    public void addEmployee(Employee employee) {
        if (employee != null) {
            employeeRepo.save(employee);
        }

    }

    // Get a Employee details by Id And this method is only accessible to ADMIN role
    //
    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getEmployeeById(@PathVariable int id) {
        try {
            Optional<Employee> employeeOptional = employeeRepo.findById(id);
            if (employeeOptional.isPresent()) {
                Employee employee = employeeOptional.get();
                return ResponseEntity.ok(employee);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("employee not found"); // Employee not found
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while fetching the employee.");
        }
    }

    // Get all Employee details
    @GetMapping("/all")
    public ResponseEntity<?> getAllEmployees() {
        try {
            List<Employee> employees = employeeRepo.findAll();

            for (Employee emp : employees) {
                System.out.println(emp.toString());
            }

            return ResponseEntity.ok(employees);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while fetching the employees.");
        }
    }

    // Get all Employee details By Job
    @GetMapping("/job/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllEmployeeByJob(@PathVariable int id) {
        try {
            Optional<Job> jobOptional = jobRepo.findById(id);
            if (jobOptional.isPresent()) {
                Job job = jobOptional.get();
                List<Employee> employees = employeeRepo.findAllByJob(job);

                for (Employee emp : employees) {
                    System.out.println(emp.toString());
                }

                return ResponseEntity.ok(employees);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("job not found"); // Job not found
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while fetching the employees by job.");
        }
    }

    // Get all Employee details By Department
    @GetMapping("/department/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllEmployeeByDept(@PathVariable int id) {
        try {
            Optional<Department> departmentOptional = departmentRepo.findById(id);
            if (departmentOptional.isPresent()) {
                Department dept = departmentOptional.get();
                List<Employee> employees = employeeRepo.findAllByDepartment(dept);

                for (Employee employee : employees) {
                    System.out.println(employee.toString());
                }

                return ResponseEntity.ok(employees);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Department not found"); // Department not found
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while fetching employees by department.");
        }
    }

    // Get all Employee details Where Employee name matches
    @GetMapping("/name/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getEmployeeByName(@PathVariable String name) {
        List<Employee> employees = employeeRepo.findAllByEmpNameContainingIgnoreCase(name);

        if (employees.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Name not found");
        }

        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }

        return ResponseEntity.ok(employees);
    }

    // Get all Employee There Employee Salary between the given range
    @GetMapping("/salary")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getEmployeeSalaryBetweenRange(@RequestParam("min") float min,
            @RequestParam("max") float max) {
        List<Employee> employees = employeeRepo.findAllBySalaryBetween(min, max);

        if (employees.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Salary not found");
        }

        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }

        return ResponseEntity.ok(employees);
    }

    // Get all Employee There Total experience is greater than or equal to given
    // number
    @GetMapping("/experience")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Employee> getExperienceEmployee(@RequestParam("years") int years) {
        List<Employee> emp = employeeRepo.findAll();
        List<Employee> empList = new ArrayList<>();

        for (Employee employee : emp) {
            int ex = jobHistoryController.getExperience(employee);
            if (ex >= years) {
                empList.add(employee);
            }
        }

        return empList;
    }

    // Updating the Employee Salary
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateEmployee(@PathVariable int id, @RequestBody Employee emp) {
        try {
            Optional<Employee> employeeOptional = employeeRepo.findById(id);
            if (employeeOptional.isPresent()) {
                Employee employee = employeeOptional.get();
                employee.setSalary(emp.getSalary());
                employeeRepo.save(employee);

                // Log the updated employee
                System.out.println("Updated employee: " + employee.toString());
                return ResponseEntity.ok().build();
            } else {
                // Log the error and return a 404 response
                System.out.println("Employee with ID " + id + " not found.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
            }
        } catch (Exception ex) {
            // Log the error and return a 500 response
            System.out.println("An error occurred while updating the employee: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while updating the employee.");
        }
    }
}
