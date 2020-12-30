package com.krzysztoffaj.companymanager.services;

import com.krzysztoffaj.companymanager.exceptions.notfound.TeamNotFoundException;
import com.krzysztoffaj.companymanager.model.domain.entities.Team;
import com.krzysztoffaj.companymanager.model.web.requests.CreateTeamRequest;
import com.krzysztoffaj.companymanager.model.web.requests.EditTeamRequest;
import com.krzysztoffaj.companymanager.repositories.TeamsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamsService {

    private final TeamsRepository teamsRepository;


    public Team getTeam(int id) {
        return teamsRepository.findById(id).orElseThrow(TeamNotFoundException::new);
    }

    public List<Team> getAllTeams() {
        return teamsRepository.findAll();
    }

    public Team createTeam(CreateTeamRequest team) {
//        teamsRepository.save(team);
//        employeesService.addTeamToManagingEmployees(team);
        return null;
    }

    public void editTeam(int id, EditTeamRequest request) {

    }

    public void deleteTeam(int id) {
        teamsRepository.delete(this.getTeam(id));
    }



}
