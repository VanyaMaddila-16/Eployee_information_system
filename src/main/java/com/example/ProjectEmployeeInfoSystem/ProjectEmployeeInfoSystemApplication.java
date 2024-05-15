package com.example.ProjectEmployeeInfoSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.ProjectEmployeeInfoSystem.Controller.DepartmentController;
import com.example.ProjectEmployeeInfoSystem.Controller.EmployeeController;
import com.example.ProjectEmployeeInfoSystem.Controller.JobController;
import com.example.ProjectEmployeeInfoSystem.Controller.JobHistoryController;

@SpringBootApplication
public class ProjectEmployeeInfoSystemApplication implements CommandLineRunner {
	@Autowired
	JobController jobController;

	@Autowired
	DepartmentController departmentController;

	@Autowired
	EmployeeController employeeController;

	@Autowired
	JobHistoryController jobHistoryController;

	public static void main(String[] args) {
		SpringApplication.run(ProjectEmployeeInfoSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("oqmwdqlidhz-----------------lifuu");
	}
}
