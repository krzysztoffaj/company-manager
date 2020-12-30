package com.krzysztoffaj.companymanager.services;

import com.krzysztoffaj.companymanager.exceptions.notfound.EmployeeNotFoundException;
import com.krzysztoffaj.companymanager.model.domain.entities.Employee;
import com.krzysztoffaj.companymanager.model.domain.entities.EmployeeWithTeamIds;
import com.krzysztoffaj.companymanager.model.domain.entities.Team;
import com.krzysztoffaj.companymanager.repositories.EmployeesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmployeesService {

    private final TeamsService teamService;
    private final EmployeesRepository employeeRepository;


    public Employee getEmployee(int id) {
        return employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    public void addTeamToManagingEmployees(Team newTeam) {
        Employee pm = getEmployee(newTeam.getPmId());
        Employee po = getEmployee(newTeam.getPoId());
        Employee scrummaster = getEmployee(newTeam.getScrummasterId());

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

    public Employee saveEmployee(EmployeeWithTeamIds employeeWithTeamIds) {
        final Employee employee = employeeWithTeamIds.getEmployee();

        Set<Team> teams = new HashSet<>();
        for (int teamId : employeeWithTeamIds.getTeams()) {
            teams.add(teamService.getTeam(teamId));
        }
        employee.setTeams(teams);

        save(employee);
        return employee;
    }

    public void deleteEmployee(int id) {
        employeeRepository.delete(this.getEmployee(id));
    }

}
