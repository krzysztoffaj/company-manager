package com.krzysztoffaj.companymanager.repositories;

import com.krzysztoffaj.companymanager.model.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface EmployeesRepository extends JpaRepository<Employee, Integer> {

    Set<Employee> findByIdIn(List<Integer> ids);

}
