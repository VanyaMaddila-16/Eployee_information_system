package com.example.ProjectEmployeeInfoSystem.Repository;

import com.example.ProjectEmployeeInfoSystem.Entity.Employee;
import com.example.ProjectEmployeeInfoSystem.Entity.JobHistory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;






@Repository
public interface JobHistoryRepo extends JpaRepository<JobHistory,Integer> {
    List<JobHistory> findAllByEmployee(Employee emp);
}