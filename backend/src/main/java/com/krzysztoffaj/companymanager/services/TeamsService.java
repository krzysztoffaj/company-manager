package com.krzysztoffaj.companymanager.services;

import com.krzysztoffaj.companymanager.exceptions.notfound.TeamNotFoundException;
import com.krzysztoffaj.companymanager.model.domain.entities.Team;
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

    public List<Team> getAll() {
        return teamRepository.findAll();
    }

    public void save(Team team) {
        teamRepository.save(team);
    }

    public Team castQueryParamsToTeamObject(String teamName, String pmId, String poId, String scrummasterId) {
        Team team = new Team();
        team.setName(teamName);
        team.setPmId(getIntFromStringOrNull(pmId));
        team.setPoId(getIntFromStringOrNull(poId));
        team.setScrummasterId(getIntFromStringOrNull(scrummasterId));

        return team;
    }

    public void updateTeamInfo(Integer teamId, String pmId, String poId, String scrummasterId) {
        Team team = getTeam(teamId);

        team.setPmId(getIntFromStringOrNull(pmId));
        team.setPoId(getIntFromStringOrNull(poId));
        team.setScrummasterId(getIntFromStringOrNull(scrummasterId));

        save(team);
    }

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
