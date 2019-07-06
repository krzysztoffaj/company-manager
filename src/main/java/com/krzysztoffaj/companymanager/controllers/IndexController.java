package com.krzysztoffaj.companymanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String goHome() {
        return "redirect:/employees";
    }
}