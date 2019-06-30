package com.krzysztoffaj.companymanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddNewEmployeeController {

    @GetMapping("/addnewemployee")
    public String addNewEmployeeInit(Model model) {

        return "addnewemployee";
    }

    @PostMapping("/addnewemployee")
    public String addNewEmployeeSubmit(Model model, String input) {

        return "search";
    }
}
