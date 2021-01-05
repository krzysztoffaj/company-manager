package com.krzysztoffaj.companymanager.services;

import com.krzysztoffaj.companymanager.exceptions.notfound.EmployeeNotFoundException;
import com.krzysztoffaj.companymanager.model.domain.entities.Employee;
import com.krzysztoffaj.companymanager.model.domain.entities.Team;
import com.krzysztoffaj.companymanager.model.domain.enums.EmployeePosition;
import com.krzysztoffaj.companymanager.model.web.requests.CreateEmployeeRequest;
import com.krzysztoffaj.companymanager.model.web.requests.EditEmployeeRequest;
import com.krzysztoffaj.companymanager.repositories.EmployeesRepository;
import com.krzysztoffaj.companymanager.specifications.EmployeesSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmployeesService {

    private final TeamsService teamsService;
    private final EmployeesRepository employeesRepository;


    public Employee getEmployee(int id) {
        return employeesRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> getAll() {
        return employeesRepository.findAll();
    }

    public Set<Employee> getEmployeesByIds(List<Integer> ids) {
        return employeesRepository.findByIdIn(ids);
    }

    public Set<Employee> getEmployeesByPosition(EmployeePosition position) {
        return employeesRepository.findByPosition(position);
    }

    public Set<Employee> getEmployeesByInput(String input) {
        final List<String> values = new ArrayList<>();
        values.add("tak");
        values.add("nie");
        return new HashSet<>(employeesRepository.findAll(EmployeesSpecification.hasValuesIterated(values)));
    }

    private String[] getWordsExtractedFromQuery(String query) {
        if (!query.matches(".*\\w.*")) {
            return new String[0];
        }
        return query.trim().split("\\s+");
    }

    public String prepareTypedQuery(String input) {
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

    public Employee createEmployee(CreateEmployeeRequest request) {
        final Employee employee = new Employee();
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setSalary(request.getSalary());
        employee.setPosition(EmployeePosition.getStatusByName(request.getPosition()));
        employee.setSupervisor(this.getEmployee(request.getSupervisorId()));
        employee.setTeams(teamsService.getTeamsByIds(request.getTeamsIds()));

        return employeesRepository.save(employee);
    }

    @Transactional
    public void editEmployee(int id, EditEmployeeRequest request) {
        final Employee employee = this.getEmployee(id);
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setSalary(request.getSalary());
        employee.setPosition(EmployeePosition.getStatusByName(request.getPosition()));
        employee.setSupervisor(this.getEmployee(request.getSupervisorId()));
        employee.setTeams(teamsService.getTeamsByIds(request.getTeamsIds()));
    }

    public void deleteEmployee(int id) {
        employeesRepository.delete(this.getEmployee(id));
    }

}
