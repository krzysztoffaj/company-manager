package com.krzysztoffaj.companymanager.services;

import com.krzysztoffaj.companymanager.model.domain.entities.Employee;
import com.krzysztoffaj.companymanager.model.domain.entities.EmployeeWithTeamIds;
import com.krzysztoffaj.companymanager.model.domain.entities.Team;

import java.util.List;

public interface EmployeeService {
    Employee get(Integer id);

    List<Employee> getAll();

    void save(Employee employee);

    void addTeamToManagingEmployees(Team newTeam);

    String prepareTypedQuery(String input);

    Employee saveEmployee(EmployeeWithTeamIds employeeWithTeamIds);

    void deleteEmployee(Employee employee);
}
