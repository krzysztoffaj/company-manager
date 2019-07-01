package com.krzysztoffaj.companymanager.services;

import com.krzysztoffaj.companymanager.entities.Employee;
import com.krzysztoffaj.companymanager.entities.Team;
import com.krzysztoffaj.companymanager.infrastructure.EmployeePosition;
import com.krzysztoffaj.companymanager.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DefaultEmployeeService implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    TeamService teamService;

    @Override
    public Employee get(int id) {
        return employeeRepository.getOne(id);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> findByFirstName(String firstName) {
        return employeeRepository.findEmployeeByFirstName(firstName);
    }

    @Override
    public List<Employee> findByLastName(String lastName) {
        return employeeRepository.findEmployeeByLastName(lastName);
    }

    @Override
    public List<Employee> findByPosition(EmployeePosition position) {
        return employeeRepository.findEmployeeByPosition(position);
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Set<Employee> handleSearching(String input) {
        String[] words = getWordsExtractedFromInput(input);

        Set<Employee> uniqueResultsFirstWord;
        Set<Employee> uniqueResultsSecondWord;
        Set<Employee> uniqueResultsThirdWord;

        Set<Employee> results = new HashSet<>();

        switch (words.length) {
            case 0:
                results = new HashSet<>(getAll());
                break;
            case 1:
                results = getUniqueResults(words[0]);
                break;
            case 2:
                uniqueResultsFirstWord = getUniqueResults(words[0]);
                uniqueResultsSecondWord = getUniqueResults(words[1]);

                results = new HashSet<>(uniqueResultsFirstWord);
                results.retainAll(uniqueResultsSecondWord);
                break;
            case 3:
                uniqueResultsFirstWord = getUniqueResults(words[0]);
                uniqueResultsSecondWord = getUniqueResults(words[1]);
                uniqueResultsThirdWord = getUniqueResults(words[2]);

                results = new HashSet<>(uniqueResultsFirstWord);
                results.retainAll(uniqueResultsSecondWord);
                results.retainAll(uniqueResultsThirdWord);
                break;
        }

        return results;
    }

    @Override
    public Set<Employee> getUniqueResults(String word) {
        List<Employee> foundByFirstName = findByFirstName(word);
        List<Employee> foundByLastName = findByLastName(word);
        List<Employee> foundByPosition;
        try {
            foundByPosition = findByPosition(EmployeePosition.valueOf(word));
        } catch (IllegalArgumentException ex) {
            foundByPosition = new ArrayList<>();
        }

        Set<Employee> uniqueResults = new HashSet<>();
        uniqueResults.addAll(foundByFirstName);
        uniqueResults.addAll(foundByLastName);
        uniqueResults.addAll(foundByPosition);

        return uniqueResults;
    }

    @Override
    public String[] getWordsExtractedFromInput(String input) {
        //TODO Only alphanumeric input. Cannot be more than 3 words
        if (!input.matches(".*\\w.*")) {
            return new String[0];
        }
        return input.trim().split("\\s+");
    }

    @Override
    public Employee castInputsToEmployeeObject(String firstName, String lastName, EmployeePosition position, String salary, int supervisorId, int[] teamsIds) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setPosition(position);
        employee.setSalary(Double.parseDouble(salary));
        employee.setSupervisorId(supervisorId);

        Set<Team> teams = new HashSet<>();
        for (int teamsId : teamsIds) {
            teams.add(teamService.get(teamsId));
        }
        employee.setTeams(teams);

        return employee;
    }
}
