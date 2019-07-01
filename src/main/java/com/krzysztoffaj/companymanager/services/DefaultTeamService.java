package com.krzysztoffaj.companymanager.services;

import com.krzysztoffaj.companymanager.entities.Team;
import com.krzysztoffaj.companymanager.infrastructure.EmployeePosition;
import com.krzysztoffaj.companymanager.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultTeamService implements TeamService {

    @Autowired
    TeamRepository teamRepository;

    @Override
    public Team get(int id) {
        return teamRepository.getOne(id);
    }

    @Override
    public List<Team> getAll() {
        return teamRepository.findAll();
    }

    @Override
    public void save(Team team) {
        teamRepository.save(team);
    }

    @Override
    public Team castInputToTeamObject(String teamName, EmployeePosition pm, EmployeePosition po, EmployeePosition scrummaster) {
        Team team = new Team();
        team.setName(teamName);

    }
}
