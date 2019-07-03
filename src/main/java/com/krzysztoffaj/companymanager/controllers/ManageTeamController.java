package com.krzysztoffaj.companymanager.controllers;

import com.krzysztoffaj.companymanager.entities.Employee;
import com.krzysztoffaj.companymanager.entities.Team;
import com.krzysztoffaj.companymanager.services.EmployeeService;
import com.krzysztoffaj.companymanager.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ManageTeamController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private TeamService teamService;

    @GetMapping("/manageteam/add")
    public String addNewTeamInit(Model model) {
        final List<Employee> allEmployees = employeeService.getAll();
        final List<Team> allTeams = teamService.getAll();
        model.addAttribute("allEmployees", allEmployees);
        model.addAttribute("allTeams", allTeams);

        Team team = null;
        model.addAttribute("team", team);

        return "manageteam";
    }

    @GetMapping("/manageteam/add/submit")
    public String addNewTeamSubmit(@RequestParam("teamName") String teamName,
                                   @RequestParam("pmId") String pmId,
                                   @RequestParam("poId") String poId,
                                   @RequestParam("scrummasterId") String scrummasterId) {
        final Team team = teamService.castInputToTeamObject(teamName, pmId, poId, scrummasterId);
        teamService.save(team);
        employeeService.addTeamToManagingEmployees(team);

        return "browseteams";
    }

    @GetMapping("/manageteam/edit/{teamId}")
    public String editEmployeeInit(@PathVariable("teamId") Integer teamId,
                                   Model model) {
        final List<Employee> allEmployees = employeeService.getAll();
        final List<Team> allTeams = teamService.getAll();
        model.addAttribute("allEmployees", allEmployees);
        model.addAttribute("allTeams", allTeams);

        try {
            Team editedTeam = teamService.get(teamId);
            System.out.println(editedTeam);
            model.addAttribute("team", editedTeam);
        } catch (Exception e) {
            return "entitynotfound";
        }

        return "manageteam";
    }

    @GetMapping("/manageteam/edit/submit")
    public String editEmployeeSubmit(@RequestParam("teamId") Integer teamId,
                                     @RequestParam("pmId") String pmId,
                                     @RequestParam("poId") String poId,
                                     @RequestParam("scrummasterId") String scrummasterId) {
        teamService.updateTeamInfo(teamId, pmId, poId, scrummasterId);
        employeeService.addTeamToManagingEmployees(teamService.get(teamId));

        return "browseteams";
    }
}