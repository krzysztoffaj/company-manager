package com.krzysztoffaj.companymanager.controllers;

import com.krzysztoffaj.companymanager.entities.Employee;
import com.krzysztoffaj.companymanager.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class BrowseEmployeesController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/browseemployees")
    public String browseEmployeesInit(Model model) {
        final List<Employee> allEmployees = employeeService.getAll();
        model.addAttribute("allEmployees", allEmployees);

        return "browseemployees";
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAll();
    }

    @PostMapping("/browseemployees")
    public String browseEmployeesSubmit(Model model, String input) {
        final Set<Employee> searchResults = employeeService.handleSearching(input);
        model.addAttribute("allEmployees", searchResults);

        return "browseemployees";
    }
}