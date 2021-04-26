package com.krzysztoffaj.companymanager.repositories;

import com.krzysztoffaj.companymanager.model.domain.entities.Employee;
import com.krzysztoffaj.companymanager.model.domain.enums.EmployeePosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface EmployeesRepository extends JpaRepository<Employee, Integer>, JpaSpecificationExecutor<Employee> {

    Set<Employee> findByIdIn(List<Integer> ids);

    Set<Employee> findByPosition(EmployeePosition position);

}
