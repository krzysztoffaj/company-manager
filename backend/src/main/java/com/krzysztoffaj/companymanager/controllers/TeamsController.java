package com.krzysztoffaj.companymanager.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.krzysztoffaj.companymanager.mappers.TeamsMapper;
import com.krzysztoffaj.companymanager.model.domain.entities.Employee;
import com.krzysztoffaj.companymanager.model.domain.entities.Team;
import com.krzysztoffaj.companymanager.model.web.dtos.TeamDto;
import com.krzysztoffaj.companymanager.model.web.requests.CreateTeamRequest;
import com.krzysztoffaj.companymanager.model.web.requests.EditTeamRequest;
import com.krzysztoffaj.companymanager.services.EmployeesService;
import com.krzysztoffaj.companymanager.services.TeamsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/teams")
public class TeamsController {

    private final TeamsService teamsService;
    private final TeamsMapper teamsMapper;


//    @GetMapping("/teams")
//    public ModelAndView setupTeamsView() {
//        modelAndView.setViewName("teams");
//        return modelAndView;
//    }

    @GetMapping("/{id}")
    public TeamDto getTeam(@PathVariable("id") int id) {
        return teamsMapper.mapToDto(teamsService.getTeam(id));
    }

    @GetMapping("/teams/list-all")
    public List<Team> getAllTeams() {
        return teamsService.getAllTeams();
    }
//
//    @GetMapping("/teams/add")
//    public ModelAndView setupAddNewTeamView() {
//        modelAndView.addObject("allEmployees", employeesService.getAll());
//        modelAndView.setViewName("add-team");
//        return modelAndView;
//    }

    @PostMapping("/teams")
    public ResponseEntity<?> createTeam(@RequestBody @Valid CreateTeamRequest request) {
        final Team team = teamsService.createTeam(request);

        final URI uri = MvcUriComponentsBuilder
                .fromMethodName(TeamsController.class, "getTeam", team.getId())
                .buildAndExpand(team.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

//    @GetMapping("/teams/edit/{id}")
//    public ModelAndView setupEditTeamView(@PathVariable("id") Integer id) {
//        try {
//            Team editedTeam = teamsService.getTeam(id);
//            System.out.println(editedTeam);
//            modelAndView.addObject("editedTeam", editedTeam);
//            modelAndView.addObject("allEmployees", employeesService.getAll());
//            modelAndView.setViewName("edit-team");
//        } catch (Exception e) {
//            modelAndView.setViewName("entity-not-found");
//        }
//        return modelAndView;
//    }

    @PutMapping("/teams/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editTeam(@PathVariable("id") int id,
                         @RequestBody @Valid EditTeamRequest request) {
        teamsService.editTeam(id, request);
    }

    @DeleteMapping("/teams/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeam(@PathVariable("id") int id) {
        teamsService.deleteTeam(id);
    }

}
