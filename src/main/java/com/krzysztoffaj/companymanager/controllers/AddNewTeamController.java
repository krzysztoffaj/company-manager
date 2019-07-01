package com.krzysztoffaj.companymanager.controllers;

import com.krzysztoffaj.companymanager.entities.Employee;
import com.krzysztoffaj.companymanager.entities.Team;
import com.krzysztoffaj.companymanager.infrastructure.EmployeePosition;
import com.krzysztoffaj.companymanager.services.EmployeeService;
import com.krzysztoffaj.companymanager.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AddNewTeamController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private TeamService teamService;

    @GetMapping("/addnewteam")
    public String addNewTeamInit(Model model) {
        final List<Employee> allEmployees = employeeService.getAll();
        final List<Team> allTeams = teamService.getAll();
        model.addAttribute("allEmployees", allEmployees);
        model.addAttribute("allTeams", allTeams);

        return "addnewteam";
    }

    @GetMapping("/addnewteamsubmit")
    public String addNewTeamSubmit(@RequestParam("teamName") String teamName,
                                   @RequestParam("pm") int pm,
                                   @RequestParam("po") int po,
                                   @RequestParam("scrummaster") int scrummaster) {
        final Team team = teamService.castInputToTeamObject(teamName, pm, po, scrummaster);
        teamService.save(team);

        return "search";
    }
}