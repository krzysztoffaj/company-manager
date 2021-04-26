package com.krzysztoffaj.companymanager.controllers;

import com.krzysztoffaj.companymanager.mappers.TeamsMapper;
import com.krzysztoffaj.companymanager.model.domain.entities.Team;
import com.krzysztoffaj.companymanager.model.web.dtos.TeamDto;
import com.krzysztoffaj.companymanager.model.web.requests.CreateTeamRequest;
import com.krzysztoffaj.companymanager.model.web.requests.EditTeamRequest;
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


    @GetMapping("/{id}")
    public TeamDto getTeam(@PathVariable("id") int id) {
        return teamsMapper.mapToDto(teamsService.getTeam(id));
    }

    @GetMapping
    public List<TeamDto> getAllTeams() {
        return teamsMapper.mapToDtos(teamsService.getAllTeams());
    }

    @PostMapping
    public ResponseEntity<?> createTeam(@RequestBody @Valid CreateTeamRequest request) {
        final Team team = teamsService.createTeam(request);

        final URI uri = MvcUriComponentsBuilder
                .fromMethodName(TeamsController.class, "getTeam", team.getId())
                .buildAndExpand(team.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editTeam(@PathVariable("id") int id,
                         @RequestBody @Valid EditTeamRequest request) {
        teamsService.editTeam(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeam(@PathVariable("id") int id) {
        teamsService.deleteTeam(id);
    }

}
