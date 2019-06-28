package com.krzysztoffaj.companymanager.services;

import com.krzysztoffaj.companymanager.entities.Employee;
import com.krzysztoffaj.companymanager.infrastructure.EmployeePosition;
import com.krzysztoffaj.companymanager.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultEmployeeService implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

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
}
