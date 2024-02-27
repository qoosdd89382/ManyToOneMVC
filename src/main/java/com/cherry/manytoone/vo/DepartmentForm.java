package com.cherry.manytoone.vo;

import com.cherry.manytoone.po.Employee;

import java.util.ArrayList;
import java.util.List;

public class DepartmentForm {
    private String departmentName;
    private String existDepartmentId;
    private List<Employee> employees = new ArrayList<>();

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getExistDepartmentId() {
        return existDepartmentId;
    }

    public void setExistDepartmentId(String existDepartmentId) {
        this.existDepartmentId = existDepartmentId;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
