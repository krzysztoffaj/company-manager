package com.krzysztoffaj.companymanager.controllers;

import com.krzysztoffaj.companymanager.entities.Employee;
import com.krzysztoffaj.companymanager.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@Controller
public class SearchController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/search")
    public String searchInit(Model model) {
        final Set<Employee> allEmployees = service.handleSearching("");
        model.addAttribute("allEmployees", allEmployees);

        return "search";
    }

    @PostMapping("/search")
    public String searchSubmit(Model model, String input) {
        final Set<Employee> searchResults = service.handleSearching(input);
        model.addAttribute("allEmployees", searchResults);

        return "search";
    }
}