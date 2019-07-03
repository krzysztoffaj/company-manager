package com.krzysztoffaj.companymanager.controllers;

import com.krzysztoffaj.companymanager.entities.Employee;
import com.krzysztoffaj.companymanager.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Set;

@Controller
public class BrowseEmployeesController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/browseemployees")
    public String browseEmployeesInit(Model model) {
        final List<Employee> allEmployees = service.getAll();
        model.addAttribute("allEmployees", allEmployees);

        return "browseemployees";
    }

    @PostMapping("/browseemployees")
    public String browseEmployeesSubmit(Model model, String input) {
        final Set<Employee> searchResults = service.handleSearching(input);
        model.addAttribute("allEmployees", searchResults);

        return "browseemployees";
    }
}