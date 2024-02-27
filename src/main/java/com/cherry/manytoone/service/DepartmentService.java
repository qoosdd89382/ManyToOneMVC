package com.cherry.manytoone.service;

import com.cherry.manytoone.dao.DepartmentRepository;
import com.cherry.manytoone.po.Department;
import com.cherry.manytoone.po.Employee;
import com.cherry.manytoone.vo.DepartmentForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department saveDepartment(Department department) {
        // 在這裡可以添加任何業務邏輯，比如驗證等
        return departmentRepository.save(department);
    }

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public void saveDepartmentWithEmployees(DepartmentForm departmentForm) {
        Department department;
        String existDepartmentId = departmentForm.getExistDepartmentId();
        if (existDepartmentId != null && !"".equals(existDepartmentId)) {
            Optional<Department> foundDept = departmentRepository.findById(Long.valueOf(existDepartmentId));
            department = foundDept.orElseThrow();

            List<Employee> preparedToSaveEmployees = departmentForm.getEmployees();
            preparedToSaveEmployees.forEach(employee -> employee.setDepartment(department));
            department.addEmployees(preparedToSaveEmployees);
        } else {
            department = new Department();
            department.setName(departmentForm.getDepartmentName());

            List<Employee> preparedToSaveEmployees = departmentForm.getEmployees();
            preparedToSaveEmployees.forEach(employee -> employee.setDepartment(department));
            department.setEmployees(preparedToSaveEmployees);
        }
        departmentRepository.save(department);
    }

}
