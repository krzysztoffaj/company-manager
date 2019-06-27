package com.krzysztoffaj.companymanager.repositories;

import com.krzysztoffaj.companymanager.entities.Employee;
import com.krzysztoffaj.companymanager.infrastructure.competences.CompetenceResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

//    public Employee get(int id) {
//        Employee employee = super.(id);
//
//        CompetenceResolver resolver = new CompetenceResolver();
//        employee.setCompetence(resolver.getCompetence(employee));
//
//        return employee;
//    }
}
