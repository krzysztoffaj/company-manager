package com.krzysztoffaj.companymanager.controllers;

import com.krzysztoffaj.companymanager.models.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchController {

    @GetMapping("/search")
    public String searchForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "search";
    }

    @PostMapping("/search")
    public String searchSubmit(@ModelAttribute Employee employee) {
        return "result";
    }
}