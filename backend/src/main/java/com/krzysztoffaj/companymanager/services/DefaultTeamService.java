package com.krzysztoffaj.companymanager.services;

import com.krzysztoffaj.companymanager.model.domain.entities.Team;
import com.krzysztoffaj.companymanager.repositories.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultTeamService implements TeamService {

    @Autowired
    TeamsRepository teamRepository;

    @Override
    public Team get(Integer id) {
        if (id == null) {
            return null;
        }
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
    public Team castQueryParamsToTeamObject(String teamName, String pmId, String poId, String scrummasterId) {
        Team team = new Team();
        team.setName(teamName);
        team.setPmId(getIntFromStringOrNull(pmId));
        team.setPoId(getIntFromStringOrNull(poId));
        team.setScrummasterId(getIntFromStringOrNull(scrummasterId));

        return team;
    }

    @Override
    public void updateTeamInfo(Integer teamId, String pmId, String poId, String scrummasterId) {
        Team team = get(teamId);

        team.setPmId(getIntFromStringOrNull(pmId));
        team.setPoId(getIntFromStringOrNull(poId));
        team.setScrummasterId(getIntFromStringOrNull(scrummasterId));

        save(team);
    }

    @Override
    public void deleteTeam(Team team) {
        teamRepository.delete(team);
    }

    private Integer getIntFromStringOrNull(String input) {
        if (input.equals("null")) {
            return null;
        } else {
            return Integer.parseInt(input);
        }
    }
}
