package com.example.ProjectEmployeeInfoSystem.Entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private int empId;

    @Column(name = "emp_name")
    private String empName;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    @JsonIgnore
    private Department department;

    @Column(name = "salary")
    private float salary;

    @Column(name = "join_date")
    private LocalDate joinDate;

    @JsonIgnore
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<JobHistory> jobHistories;

    public Employee() {
    }

    public Employee(String empName, Job job, Department department, float salary, LocalDate joinDate) {
        this.empName = empName;
        this.job = job;
        this.department = department;
        this.salary = salary;
        this.joinDate = joinDate;
    }

    public int getEmpId() {
        return empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public List<JobHistory> getJobHistories() {
        return jobHistories;
    }

    public void setJobHistories(List<JobHistory> jobHistories) {
        this.jobHistories = jobHistories;
    }

    @Override
    public String toString() {
        return "Employee [empId=" + empId + ", empName=" + empName + ", job=" + job + ", department=" + department
                + ", salary=" + salary + ", joinDate=" + joinDate + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + empId;
        result = prime * result + ((empName == null) ? 0 : empName.hashCode());
        result = prime * result + ((job == null) ? 0 : job.hashCode());
        result = prime * result + ((department == null) ? 0 : department.hashCode());
        result = prime * result + Float.floatToIntBits(salary);
        result = prime * result + ((joinDate == null) ? 0 : joinDate.hashCode());
        result = prime * result + ((jobHistories == null) ? 0 : jobHistories.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Employee other = (Employee) obj;
        if (empId != other.empId)
            return false;
        if (empName == null) {
            if (other.empName != null)
                return false;
        } else if (!empName.equals(other.empName))
            return false;
        if (job == null) {
            if (other.job != null)
                return false;
        } else if (!job.equals(other.job))
            return false;
        if (department == null) {
            if (other.department != null)
                return false;
        } else if (!department.equals(other.department))
            return false;
        if (Float.floatToIntBits(salary) != Float.floatToIntBits(other.salary))
            return false;
        if (joinDate == null) {
            if (other.joinDate != null)
                return false;
        } else if (!joinDate.equals(other.joinDate))
            return false;
        if (jobHistories == null) {
            if (other.jobHistories != null)
                return false;
        } else if (!jobHistories.equals(other.jobHistories))
            return false;
        return true;
    }

}
