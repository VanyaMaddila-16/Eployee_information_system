package com.example.ProjectEmployeeInfoSystem.Repository;

import com.example.ProjectEmployeeInfoSystem.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DepartmentRepo extends JpaRepository<Department,Integer> {
    
}
