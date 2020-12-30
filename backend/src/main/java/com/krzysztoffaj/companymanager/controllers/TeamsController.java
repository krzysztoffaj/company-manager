package com.krzysztoffaj.companymanager.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.krzysztoffaj.companymanager.model.domain.entities.Team;
import com.krzysztoffaj.companymanager.exceptions.badrequest.InvalidSalaryException;
import com.krzysztoffaj.companymanager.infrastructure.View;
import com.krzysztoffaj.companymanager.services.EmployeesService;
import com.krzysztoffaj.companymanager.services.TeamsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/teams")
public class TeamsController {

    private final EmployeesService employeeService;
    private final TeamsService teamService;


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
    public ModelAndView setupAddNewTeamView() {
        modelAndView.addObject("allEmployees", employeeService.getAll());
        modelAndView.setViewName("add-team");
        return modelAndView;
    }

    @PostMapping("/teams/add")
    public Team addNewTeamSubmit(@Valid @RequestBody Team team) {
        teamService.save(team);
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

    @PutMapping("/teams/edit/{id}")
    public Team editTeam(@PathVariable("id") Integer id,
                         @Valid @RequestBody Team team) {
        teamService.save(team);
        employeeService.addTeamToManagingEmployees(team);

        return team;
    }

    @DeleteMapping("/teams/delete/{id}")
    public void deleteTeam(@PathVariable("id") Integer id) {
        teamService.deleteTeam(teamService.getTeam(id));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public void handleValidationError(MethodArgumentNotValidException ex) {
        System.out.println("Validation error occured!");
    }

    @ExceptionHandler(InvalidSalaryException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public void handleBadSalaryValueException(InvalidSalaryException ex) {
        System.out.println("Invalid salary provided!");
    }
}
