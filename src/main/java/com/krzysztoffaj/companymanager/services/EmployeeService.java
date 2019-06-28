package com.krzysztoffaj.companymanager.services;

import com.krzysztoffaj.companymanager.entities.Employee;
import com.krzysztoffaj.companymanager.infrastructure.EmployeePosition;

import java.util.List;

public interface EmployeeService {
    Employee get(int id);

    List<Employee> getAll();

    List<Employee> findByFirstName(String firstName);

    List<Employee> findByLastName(String lastName);

    List<Employee> findByPosition(EmployeePosition position);
}
