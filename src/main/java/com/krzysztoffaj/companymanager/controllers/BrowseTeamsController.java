package com.krzysztoffaj.companymanager.controllers;

import com.krzysztoffaj.companymanager.entities.Employee;
import com.krzysztoffaj.companymanager.entities.Team;
import com.krzysztoffaj.companymanager.services.EmployeeService;
import com.krzysztoffaj.companymanager.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BrowseTeamsController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private TeamService teamService;

    @GetMapping("/browseteams")
    public String browseTeamsInit(Model model) {
        final List<Team> allTeams = teamService.getAll();
        final List<Employee> allEmployees = employeeService.getAll();
        model.addAttribute("allTeams", allTeams);
        model.addAttribute("allEmployees", allEmployees);

        return "browseteams";
    }

    @GetMapping("/teams")
    public List<Team> getAllTeams() {
        return teamService.getAll();
    }
}
