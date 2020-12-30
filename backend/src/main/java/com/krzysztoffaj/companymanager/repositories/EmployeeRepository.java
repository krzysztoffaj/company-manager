package com.krzysztoffaj.companymanager.repositories;

import com.krzysztoffaj.companymanager.model.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
