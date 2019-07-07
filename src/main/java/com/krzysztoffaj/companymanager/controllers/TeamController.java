package com.krzysztoffaj.companymanager.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.krzysztoffaj.companymanager.entities.Team;
import com.krzysztoffaj.companymanager.infrastructure.View;
import com.krzysztoffaj.companymanager.services.EmployeeService;
import com.krzysztoffaj.companymanager.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class TeamController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private TeamService teamService;

    private ModelAndView modelAndView = new ModelAndView();

    @GetMapping("/teams")
    public ModelAndView setupTeamsView() {
        modelAndView.setViewName("teams");
        return modelAndView;
    }

    @GetMapping("/teams/list-all")
    @JsonView(View.DetailedTeamsInfo.class)
    public List<Team> getAllTeams() {
        return teamService.getAll();
    }

    @GetMapping("/teams/add")
    @JsonView(View.DetailedTeamsInfo.class)
    public ModelAndView setupAddNewTeamView() {
        modelAndView.setViewName("add-or-edit-team");
        return modelAndView;
    }

    @PostMapping("/teams/add")
    public Team addNewTeamSubmit(Team team) {
//        final Team team = teamService.castQueryParamsToTeamObject(teamName, pmId, poId, scrummasterId);
        teamService.save(team);
        employeeService.addTeamToManagingEmployees(team);

        return team;
    }

    @GetMapping("/teams/edit/{id}")
    public ModelAndView setupEditTeamView(@PathVariable("id") Integer id) {
        try {
            Team editedTeam = teamService.get(id);
            System.out.println(editedTeam);
            modelAndView.setViewName("add-or-edit-team");
        } catch (Exception e) {
            modelAndView.setViewName("entity-not-found");
        }
        return modelAndView;
    }

    @PutMapping("/teams/edit/{id}")
    public Team editTeam(@PathVariable("id") Integer id,
                           Team team) {
//        teamService.updateTeamInfo(id, pmId, poId, scrummasterId);
//        teamService.save(id);
        employeeService.addTeamToManagingEmployees(teamService.get(id));

        return team;
    }
}
