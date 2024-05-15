package com.example.ProjectEmployeeInfoSystem.Repository;

import com.example.ProjectEmployeeInfoSystem.Entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JobRepo extends JpaRepository<Job,Integer> {
}
