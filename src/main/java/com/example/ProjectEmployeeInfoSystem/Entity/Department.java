package com.example.ProjectEmployeeInfoSystem.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_id")
    private int deptId;

    @Column(name = "dept_name")
    private String deptName;

    @OneToOne
    @JoinColumn(name = "hod")
    private Employee hod;

    @JsonIgnore
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Employee> employees;

    public Department() {
    }

    public Department(String deptName) {
        this.deptName = deptName;
    }

    public int getDeptId() {
        return deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Employee getHod() {
        return hod;
    }

    public void setHod(Employee hod) {
        this.hod = hod;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Department [deptId=" + deptId + ", deptName=" + deptName + ", hod=" + hod.getEmpId() + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + deptId;
        result = prime * result + ((deptName == null) ? 0 : deptName.hashCode());
        result = prime * result + ((hod == null) ? 0 : hod.hashCode());
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
        Department other = (Department) obj;
        if (deptId != other.deptId)
            return false;
        if (deptName == null) {
            if (other.deptName != null)
                return false;
        } else if (!deptName.equals(other.deptName))
            return false;
        if (hod == null) {
            if (other.hod != null)
                return false;
        } else if (!hod.equals(other.hod))
            return false;
        if (employees == null) {
            if (other.employees != null)
                return false;
        } else if (!employees.equals(other.employees))
            return false;
        return true;
    }

}
