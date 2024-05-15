package com.example.ProjectEmployeeInfoSystem.Controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjectEmployeeInfoSystem.Entity.Employee;
import com.example.ProjectEmployeeInfoSystem.Entity.Job;
import com.example.ProjectEmployeeInfoSystem.Entity.JobHistory;
import com.example.ProjectEmployeeInfoSystem.Repository.EmployeeRepo;
import com.example.ProjectEmployeeInfoSystem.Repository.JobHistoryRepo;
import com.example.ProjectEmployeeInfoSystem.Repository.JobRepo;

@RestController
@RequestMapping("/job-history")
public class JobHistoryController {
    @Autowired
    JobHistoryRepo jobHistoryRepo;

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    JobRepo jobRepo;

    // Adding a new Job History details
    @PostMapping("/add")
    public void addJobHistory(@RequestParam("job") int job, @RequestParam("emp") int emp,
            @RequestBody JobHistory jobHistory) {
        try {
            Optional<Job> jobOptional = jobRepo.findById(job);
            Optional<Employee> empOptional = employeeRepo.findById(emp);

            if (jobOptional.isPresent() && empOptional.isPresent()) {
                Job jobId = jobOptional.get();
                Employee empId = empOptional.get();
                JobHistory history = new JobHistory();
                history.setJob(jobId);
                history.setEmployee(empId);
                history.setStartDate(jobHistory.getStartDate());
                history.setEndDate(jobHistory.getEndDate());

                jobHistoryRepo.save(history);
            } else {
                // Handle case where job or employee not found
                throw new RuntimeException("Job or employee not found");
            }
        } catch (Exception ex) {
            // Handle any other exceptions
            ex.printStackTrace(); // You can log the exception for debugging
            throw new RuntimeException("An error occurred while adding job history");
        }
    }

    // Get all Job History details
    @GetMapping("/all")
    public ResponseEntity<?> getAllJobHistories() {
        try {
            List<JobHistory> histories = jobHistoryRepo.findAll();
            return ResponseEntity.ok(histories);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while fetching job histories.");
        }
    }

    public int getExperience(Employee emp) {
        List<JobHistory> history = jobHistoryRepo.findAllByEmployee(emp);
        int years = 0;

        for (JobHistory h : history) {
            LocalDate endDate = (h.getEndDate() == null) ? LocalDate.now() : h.getEndDate();
            Period period = Period.between(h.getStartDate(), endDate);
            years += period.getYears();
        }

        return years;
    }

    // Get One Job History by Employee
    @GetMapping("/employee/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getJobHistoryByEmployee(@PathVariable int id) {
        try {
            Optional<Employee> employeeOptional = employeeRepo.findById(id);
            if (employeeOptional.isPresent()) {
                Employee emp = employeeOptional.get();
                List<JobHistory> history = jobHistoryRepo.findAllByEmployee(emp);
                return ResponseEntity.ok(history);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("employee not found"); // Employee not found
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while fetching job histories by employee.");
        }
    }

}
