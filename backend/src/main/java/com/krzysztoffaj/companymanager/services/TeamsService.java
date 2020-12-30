package com.krzysztoffaj.companymanager.services;

import com.krzysztoffaj.companymanager.exceptions.notfound.TeamNotFoundException;
import com.krzysztoffaj.companymanager.model.domain.entities.Team;
import com.krzysztoffaj.companymanager.model.web.requests.CreateTeamRequest;
import com.krzysztoffaj.companymanager.repositories.TeamsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamsService {

    private final TeamsRepository teamRepository;


    public Team getTeam(int id) {
        return teamRepository.findById(id).orElseThrow(TeamNotFoundException::new);
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public void createTeam(CreateTeamRequest team) {
        teamRepository.save(team);
    }

    public void deleteTeam(int id) {
        teamRepository.delete(this.getTeam(id));
    }



}
