package com.krzysztoffaj.companymanager.services;

import com.krzysztoffaj.companymanager.entities.Team;

import java.util.List;

public interface TeamService {
    Team get(int id);

    List<Team> getAll();
}
