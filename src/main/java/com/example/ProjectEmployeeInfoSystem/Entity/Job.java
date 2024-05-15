package com.example.ProjectEmployeeInfoSystem.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;





@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private int jobId;

    @Column(name = "job_name")
    private String jobName;

    @JsonIgnore
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private List<Employee> employees;

    public Job() {
    }

    public Job(String jobName) {
        this.jobName = jobName;
    }

    public int getJobId() {
        return jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Job [jobId=" + jobId + ", jobName=" + jobName + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + jobId;
        result = prime * result + ((jobName == null) ? 0 : jobName.hashCode());
        result = prime * result + ((employees == null) ? 0 : employees.hashCode());
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
        Job other = (Job) obj;
        if (jobId != other.jobId)
            return false;
        if (jobName == null) {
            if (other.jobName != null)
                return false;
        } else if (!jobName.equals(other.jobName))
            return false;
        if (employees == null) {
            if (other.employees != null)
                return false;
        } else if (!employees.equals(other.employees))
            return false;
        return true;
    }

    
}
