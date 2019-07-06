package com.krzysztoffaj.companymanager.services;

import com.krzysztoffaj.companymanager.entities.Team;
import com.krzysztoffaj.companymanager.infrastructure.EmployeePosition;

import java.util.List;

public interface TeamService {
    Team get(Integer id);

    List<Team> getAll();

    void save(Team team);

    Team castQueryParamsToTeamObject(String teamName, String pmId, String poId, String scrummasterId);

    void updateTeamInfo(Integer teamId, String pmId, String poId, String scrummasterId);
}
