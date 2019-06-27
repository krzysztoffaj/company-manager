package com.krzysztoffaj.companymanager.repositories;

import com.krzysztoffaj.companymanager.entities.Employee;
import com.krzysztoffaj.companymanager.infrastructure.competences.CompetenceResolver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class EmployeeRepository implements JpaRepository<Employee, Integer> {

//    public Employee get(int id) {
//        Employee employee = super.(id);
//
//        CompetenceResolver resolver = new CompetenceResolver();
//        employee.setCompetence(resolver.getCompetence(employee));
//
//        return employee;
//    }
}
