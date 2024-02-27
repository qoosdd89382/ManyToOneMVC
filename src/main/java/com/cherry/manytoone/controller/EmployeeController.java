package com.cherry.manytoone.controller;

import com.cherry.manytoone.po.Employee;
import com.cherry.manytoone.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/new")
    public String showEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", employeeService.findAllDepartments());
        return "addEmployee";
    }

    @PostMapping
    public String addEmployee(Employee employee, Model model) {
        employeeService.saveEmployee(employee);
        return "redirect:/employees/all"; // 或者其他適當的重定向路徑
    }

    @GetMapping("/all")
    public String listAllEmployees(Model model) {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "employeesList";
    }

    @GetMapping("/by-department")
    public String showEmployeesByDepartment(@RequestParam(value = "departmentId", required = false) Long departmentId, Model model) {
        model.addAttribute("departments", employeeService.findAllDepartments());
        if (departmentId != null) {
            List<Employee> employees = employeeService.findEmployeesByDepartmentId(departmentId);
            model.addAttribute("employees", employees);
        }
        return "departmentPage";
    }


}
