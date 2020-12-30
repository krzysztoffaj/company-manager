package com.krzysztoffaj.companymanager.services;

import com.krzysztoffaj.companymanager.model.domain.entities.Employee;
import com.krzysztoffaj.companymanager.model.domain.entities.EmployeeWithTeamIds;
import com.krzysztoffaj.companymanager.model.domain.entities.Team;
import com.krzysztoffaj.companymanager.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DefaultEmployeeService implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TeamService teamService;

    @Override
    public Employee get(Integer id) {
        if (id == null) {
            return null;
        }
        return employeeRepository.getOne(id);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void addTeamToManagingEmployees(Team newTeam) {
        Employee pm = get(newTeam.getPmId());
        Employee po = get(newTeam.getPoId());
        Employee scrummaster = get(newTeam.getScrummasterId());

        Employee[] employees = {pm, po, scrummaster};
        for (Employee employee : employees) {
            if (employee != null) {
                employee.getTeams().add(newTeam);
                save(employee);
            }
        }
    }

    private String[] getWordsExtractedFromQuery(String query) {
        //TODO Only alphanumeric query. Cannot be more than 3 words
        if (!query.matches(".*\\w.*")) {
            return new String[0];
        }
        return query.trim().split("\\s+");
    }

    @Override
    public String prepareTypedQuery(String input) {
        //TODO cleanup
        final String[] words = getWordsExtractedFromQuery(input);
        if(words.length == 0) {
            return "FROM Employee";
        }

        StringBuilder query = new StringBuilder();

        query.append("FROM Employee WHERE ");
        for (int i = 0; i < words.length - 1; i++) {
            query.append("(first_name LIKE '%").append(words[i]).append("%' OR ");
            query.append("last_name LIKE '%").append(words[i]).append("%' OR ");
            query.append("position LIKE '%").append(words[i]).append("%') AND ");
        }
        query.append("(first_name LIKE '%").append(words[words.length - 1]).append("%' OR ");
        query.append("last_name LIKE '%").append(words[words.length - 1]).append("%' OR ");
        query.append("position LIKE '%").append(words[words.length - 1]).append("%')");

        return query.toString();
    }

    @Override
    public Employee saveEmployee(EmployeeWithTeamIds employeeWithTeamIds) {
        final Employee employee = employeeWithTeamIds.getEmployee();

        Set<Team> teams = new HashSet<>();
        for (int teamId : employeeWithTeamIds.getTeams()) {
            teams.add(teamService.get(teamId));
        }
        employee.setTeams(teams);

        save(employee);
        return employee;
    }

    @Override
    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }
}
