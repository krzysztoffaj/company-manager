package com.krzysztoffaj.companymanager.services;

import com.krzysztoffaj.companymanager.entities.Employee;

import java.util.List;

public interface EmployeeService {
    Employee get(int id);

    List<Employee> getAll();

    List<Employee> findByInput(String input);
}
