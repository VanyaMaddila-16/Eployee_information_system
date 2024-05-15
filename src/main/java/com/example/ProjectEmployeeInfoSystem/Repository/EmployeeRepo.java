package com.example.ProjectEmployeeInfoSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProjectEmployeeInfoSystem.Entity.Department;
import com.example.ProjectEmployeeInfoSystem.Entity.Employee;
import com.example.ProjectEmployeeInfoSystem.Entity.Job;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    List<Employee> findAllByJob(Job job);

    List<Employee> findAllByDepartment(Department dept);

    List<Employee> findAllByEmpNameContainingIgnoreCase(String name);

    List<Employee> findAllBySalaryBetween(float min, float max);
}
