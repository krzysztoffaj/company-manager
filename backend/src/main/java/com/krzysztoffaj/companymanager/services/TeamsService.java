package com.krzysztoffaj.companymanager.services;

import com.krzysztoffaj.companymanager.exceptions.notfound.TeamNotFoundException;
import com.krzysztoffaj.companymanager.model.domain.entities.Team;
import com.krzysztoffaj.companymanager.model.web.requests.CreateTeamRequest;
import com.krzysztoffaj.companymanager.model.web.requests.EditTeamRequest;
import com.krzysztoffaj.companymanager.repositories.TeamsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamsService {

    private final EmployeesService employeesService;
    private final TeamsRepository teamsRepository;


    public Team getTeam(int id) {
        return teamsRepository.findById(id).orElseThrow(TeamNotFoundException::new);
    }

    public List<Team> getAllTeams() {
        return teamsRepository.findAll();
    }

    public Team createTeam(CreateTeamRequest request) {
        final Team team = new Team();
        team.setName(request.getName());
        team.setProductOwner(employeesService.getEmployee(request.getProductOwnerId()));
        team.setProjectManager(employeesService.getEmployee(request.getProjectManagerId()));
        team.setScrumMaster(employeesService.getEmployee(request.getScrumMasterId()));

        return teamsRepository.save(team);
    }

    @Transactional
    public void editTeam(int id, EditTeamRequest request) {
        final Team team = this.getTeam(id);

        team.setName(request.getName());
        team.setProductOwner(employeesService.getEmployee(request.getProductOwnerId()));
        team.setProjectManager(employeesService.getEmployee(request.getProjectManagerId()));
        team.setScrumMaster(employeesService.getEmployee(request.getScrumMasterId()));
    }

    public void deleteTeam(int id) {
        teamsRepository.delete(this.getTeam(id));
    }

}
