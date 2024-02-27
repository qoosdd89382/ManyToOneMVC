package com.cherry.manytoone.service;

import com.cherry.manytoone.dao.DepartmentRepository;
import com.cherry.manytoone.dao.EmployeeRepository;
import com.cherry.manytoone.po.Department;
import com.cherry.manytoone.po.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Department> findAllDepartments() {
        return departmentRepository.findAll();
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public List<Employee> findEmployeesByDepartmentId(Long departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }

}
