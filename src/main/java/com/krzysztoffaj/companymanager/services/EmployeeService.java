package com.krzysztoffaj.companymanager.services;

import com.krzysztoffaj.companymanager.entities.Employee;
import com.krzysztoffaj.companymanager.entities.EmployeeWithTeamIds;
import com.krzysztoffaj.companymanager.entities.Team;
import com.krzysztoffaj.companymanager.infrastructure.EmployeePosition;

import java.util.List;
import java.util.Set;

public interface EmployeeService {
    Employee get(Integer id);

    List<Employee> getAll();

    void save(Employee employee);

    void addTeamToManagingEmployees(Team newTeam);

    String prepareTypedQuery(String input);

    Employee saveEmployee(EmployeeWithTeamIds employeeWithTeamIds);
}
