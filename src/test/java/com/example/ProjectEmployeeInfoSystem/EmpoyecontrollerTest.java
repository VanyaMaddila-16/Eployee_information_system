package com.example.ProjectEmployeeInfoSystem;

//
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.ProjectEmployeeInfoSystem.Controller.EmployeeController;
import com.example.ProjectEmployeeInfoSystem.Entity.Department;
import com.example.ProjectEmployeeInfoSystem.Entity.Employee;
import com.example.ProjectEmployeeInfoSystem.Entity.Job;
import com.example.ProjectEmployeeInfoSystem.Repository.EmployeeRepo;

@ExtendWith(MockitoExtension.class)
public class EmpoyecontrollerTest {
    private MockMvc mockMvc;

    @Mock
    private EmployeeRepo employeeRepo;

    @InjectMocks
    private EmployeeController employeeController;
    Employee employee;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
        Job job = new Job("Software Engineer");
        Department dept = new Department("Software Development");
        LocalDate joinDate = LocalDate.of(2022, 04, 25);
        Employee employee1 = new Employee("virat", job, dept, 35000.00f, joinDate);

        Job job1 = new Job("Software Engineer");
        Department dept1 = new Department("Software Development");
        LocalDate joinDate1 = LocalDate.of(2022, 04, 25);
        Employee employee2 = new Employee("rani", job1, dept1, 35000.00f, joinDate1);

        // Mock the behavior of employeeRepo to return the sample employee when queried
        // when(employeeRepo.findAllByEmpNameContainingIgnoreCase("virat")).thenReturn(List.of(employee1));
        when(employeeRepo.findAllByEmpNameContainingIgnoreCase("rani")).thenReturn(List.of(employee2));

    }

    @Test
    public void testfindAllByEmpNameContainingIgnoreCase() {
        List<Employee> employeelist = employeeRepo.findAllByEmpNameContainingIgnoreCase("rani");

        // Assert that the list contains one employee
        assertEquals(1, employeelist.size());

        // Assert properties of the retrieved employee
        assertEquals("rani", employeelist.get(0).getEmpName());
    }
}
