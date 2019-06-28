package com.krzysztoffaj.companymanager.controllers;

import com.krzysztoffaj.companymanager.entities.Employee;
import com.krzysztoffaj.companymanager.infrastructure.EmployeePosition;
import com.krzysztoffaj.companymanager.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/search")
    public String searchForm(Model model) {
        model.addAttribute("employee", new Employee());

//        final List<Employee> allEmployees = service.getAll();
        final List<Employee> allEmployees = service.findByPosition(EmployeePosition.PM);
        model.addAttribute("allEmployees", allEmployees);

        return "search";
    }

    @PostMapping("/search")
    public String searchSubmit(@ModelAttribute Employee employee) {
        return "result";
    }
}