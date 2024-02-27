package com.cherry.manytoone.controller;

import com.cherry.manytoone.po.Department;
import com.cherry.manytoone.service.DepartmentService;
import com.cherry.manytoone.vo.DepartmentForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        // 創建空的部門對象供表單使用
        model.addAttribute("department", new Department());
        return "createDepartment"; // 返回Thymeleaf模板的名稱
    }

    @PostMapping
    public String createDepartment(@ModelAttribute Department department, Model model) {
        // 保存部門資料
        departmentService.saveDepartment(department);
        model.addAttribute("departments", departmentService.findAll());
        return "redirect:/departments/all"; // 重定向到顯示所有部門的頁面
    }

    @GetMapping("/all")
    public String listDepartments(Model model) {
        model.addAttribute("departments", departmentService.findAll());
        return "departmentsList"; // 返回顯示所有部門的Thymeleaf模板名稱
    }

    @GetMapping("/addDepartmentWithEmployees")
    public String showForm(Model model) {
        model.addAttribute("departmentForm", new DepartmentForm());
        model.addAttribute("departments", departmentService.findAll());
        return "addDepartmentWithEmployees";
    }

    @PostMapping("/addDepartmentWithEmployees")
    public String submitForm(@ModelAttribute DepartmentForm departmentForm) {
        departmentService.saveDepartmentWithEmployees(departmentForm);
        return "redirect:/employees/by-department";
    }
}

