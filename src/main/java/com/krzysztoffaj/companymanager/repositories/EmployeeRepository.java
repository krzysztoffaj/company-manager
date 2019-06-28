package com.krzysztoffaj.companymanager.repositories;

import com.krzysztoffaj.companymanager.entities.Employee;
import com.krzysztoffaj.companymanager.infrastructure.EmployeePosition;
import com.krzysztoffaj.companymanager.infrastructure.competences.CompetenceResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


    List<Employee> findEmployeeByFirstName(String firstName);

    List<Employee> findEmployeeByLastName(String lastName);

    List<Employee> findEmployeeByPosition(EmployeePosition position);

//    public Employee get(int id) {
//        Employee employee = super.(id);
//
//        CompetenceResolver resolver = new CompetenceResolver();
//        employee.setCompetence(resolver.getCompetence(employee));
//
//        return employee;
//    }
}
