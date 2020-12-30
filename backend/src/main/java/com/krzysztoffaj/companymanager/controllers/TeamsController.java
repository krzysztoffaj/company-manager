package com.krzysztoffaj.companymanager.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.krzysztoffaj.companymanager.model.domain.entities.Team;
import com.krzysztoffaj.companymanager.model.web.requests.EditTeamRequest;
import com.krzysztoffaj.companymanager.services.EmployeesService;
import com.krzysztoffaj.companymanager.services.TeamsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/teams")
public class TeamsController {

    private final EmployeesService employeeService;
    private final TeamsService teamService;


//    @GetMapping("/teams")
//    public ModelAndView setupTeamsView() {
//        modelAndView.setViewName("teams");
//        return modelAndView;
//    }

    @GetMapping("/teams/list-all")
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }
//
//    @GetMapping("/teams/add")
//    public ModelAndView setupAddNewTeamView() {
//        modelAndView.addObject("allEmployees", employeeService.getAll());
//        modelAndView.setViewName("add-team");
//        return modelAndView;
//    }

    @PostMapping("/teams/add")
    public Team addNewTeamSubmit(@Valid @RequestBody Team team) {
        teamService.createTeam(team);
        employeeService.addTeamToManagingEmployees(team);

        return team;
    }

    @GetMapping("/teams/edit/{id}")
    public ModelAndView setupEditTeamView(@PathVariable("id") Integer id) {
        try {
            Team editedTeam = teamService.getTeam(id);
            System.out.println(editedTeam);
            modelAndView.addObject("editedTeam", editedTeam);
            modelAndView.addObject("allEmployees", employeeService.getAll());
            modelAndView.setViewName("edit-team");
        } catch (Exception e) {
            modelAndView.setViewName("entity-not-found");
        }
        return modelAndView;
    }

    @PutMapping("/teams/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editTeam(@PathVariable("id") Integer id,
                         @RequestBody @Valid EditTeamRequest request) {
        teamService.editTeam(request);
    }

    @DeleteMapping("/teams/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeam(@PathVariable("id") int id) {
        teamService.deleteTeam(id);
    }

}
