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
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjectEmployeeInfoSystem.Entity.Job;
import com.example.ProjectEmployeeInfoSystem.Repository.JobRepo;

@RestController
@RequestMapping("/job")
public class JobController {
    @Autowired
    JobRepo jobRepo;

    // adding a new job row
    public void addJob(Job job) {
        if (job != null) {
            jobRepo.save(job);
        }
    }

    // Get a job details by job ID
    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getJobById(@PathVariable int id) {
        try {
            Optional<Job> jobOptional = jobRepo.findById(id);
            if (jobOptional.isPresent()) {
                Job job = jobOptional.get();
                return ResponseEntity.ok(job);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("job not found"); // Job not found
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while fetching the job.");
        }
    }

    // Get all job details
    @GetMapping("/all")
    public ResponseEntity<?> getAllJobs() {
        try {
            List<Job> jobs = jobRepo.findAll();

            for (Job job : jobs) {
                System.out.println(job.toString());
            }

            return ResponseEntity.ok(jobs);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while fetching the jobs.");
        }
    }
}
