package com.krzysztoffaj.companymanager.services;

import com.krzysztoffaj.companymanager.exceptions.conflict.TeamAlreadyExistsException;
import com.krzysztoffaj.companymanager.exceptions.notfound.TeamNotFoundException;
import com.krzysztoffaj.companymanager.model.domain.entities.Employee;
import com.krzysztoffaj.companymanager.model.domain.entities.Team;
import com.krzysztoffaj.companymanager.model.web.requests.CreateTeamRequest;
import com.krzysztoffaj.companymanager.model.web.requests.EditTeamRequest;
import com.krzysztoffaj.companymanager.repositories.TeamsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TeamsService {

    @Lazy
    private final EmployeesService employeesService;
    private final TeamsRepository teamsRepository;


    public Team getTeam(int id) {
        return teamsRepository.findById(id).orElseThrow(TeamNotFoundException::new);
    }

    public List<Team> getAllTeams() {
        return teamsRepository.findAll();
    }

    public Set<Team> getTeamsByIds(List<Integer> ids) {
        return teamsRepository.findByIdIn(ids);
    }

    public Team createTeam(CreateTeamRequest request) {
        if (teamsRepository.existsByName(request.getName())) {
            throw new TeamAlreadyExistsException();
        }

        final Employee productOwner = employeesService.getEmployee(request.getProductOwnerId());
        final Employee projectManager = employeesService.getEmployee(request.getProjectManagerId());
        final Employee scrumMaster = employeesService.getEmployee(request.getScrumMasterId());
        final Set<Employee> otherMembers = employeesService.getEmployees(request.getEmployeesIds());

        final Set<Employee> allMembers = new HashSet<>(otherMembers);
        allMembers.addAll(Arrays.asList(productOwner, projectManager, scrumMaster));

        final Team team = new Team();
        team.setName(request.getName());
        team.setProductOwner(productOwner);
        team.setProjectManager(projectManager);
        team.setScrumMaster(scrumMaster);
        team.setEmployees(allMembers);

        return teamsRepository.save(team);
    }

    @Transactional
    public void editTeam(int id, EditTeamRequest request) {
        final Team team = this.getTeam(id);

        if (!team.getName().equals(request.getName()) && teamsRepository.existsByName(request.getName())) {
            throw new TeamAlreadyExistsException();
        }

        final Employee productOwner = employeesService.getEmployee(request.getProductOwnerId());
        final Employee projectManager = employeesService.getEmployee(request.getProjectManagerId());
        final Employee scrumMaster = employeesService.getEmployee(request.getScrumMasterId());
        final Set<Employee> otherMembers = employeesService.getEmployees(request.getEmployeesIds());

        final Set<Employee> allMembers = new HashSet<>(otherMembers);
        allMembers.addAll(Arrays.asList(productOwner, projectManager, scrumMaster));

        team.setName(request.getName());
        team.setProductOwner(productOwner);
        team.setProjectManager(projectManager);
        team.setScrumMaster(scrumMaster);
        team.setEmployees(allMembers);
    }

    public void deleteTeam(int id) {
        teamsRepository.delete(this.getTeam(id));
    }

}
