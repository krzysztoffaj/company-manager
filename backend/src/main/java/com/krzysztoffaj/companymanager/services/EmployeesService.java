package com.krzysztoffaj.companymanager.services;

import com.krzysztoffaj.companymanager.exceptions.notfound.EmployeeNotFoundException;
import com.krzysztoffaj.companymanager.model.domain.entities.Employee;
import com.krzysztoffaj.companymanager.model.domain.enums.EmployeePosition;
import com.krzysztoffaj.companymanager.model.web.requests.CreateEmployeeRequest;
import com.krzysztoffaj.companymanager.model.web.requests.EditEmployeeRequest;
import com.krzysztoffaj.companymanager.repositories.EmployeesRepository;
import com.krzysztoffaj.companymanager.specifications.EmployeesSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmployeesService {

    private final TeamsService teamsService;
    private final EmployeesRepository employeesRepository;


    public Employee getEmployee(int id) {
        return employeesRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> getAll() {
        return employeesRepository.findAll();
    }

    public Set<Employee> getEmployees(List<Integer> ids) {
        return employeesRepository.findByIdIn(ids);
    }

    public Set<Employee> getEmployeesByPosition(EmployeePosition position) {
        return employeesRepository.findByPosition(position);
    }

    public Set<Employee> getEmployeesByInput(String input) {
        final String[] values = input.split(" ");
        return new HashSet<>(employeesRepository.findAll(EmployeesSpecification.hasValuesIterated(values)));
    }

    public Employee createEmployee(CreateEmployeeRequest request) {
        final Employee employee = new Employee();
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setSalary(request.getSalary());
        employee.setPosition(EmployeePosition.getStatusByName(request.getPosition()));
        employee.setSupervisor(this.getEmployee(request.getSupervisorId()));
        employee.setTeams(teamsService.getTeamsByIds(request.getTeamsIds()));

        return employeesRepository.save(employee);
    }

    @Transactional
    public void editEmployee(int id, EditEmployeeRequest request) {
        final Employee employee = this.getEmployee(id);
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setSalary(request.getSalary());
        employee.setPosition(EmployeePosition.getStatusByName(request.getPosition()));
        employee.setSupervisor(this.getEmployee(request.getSupervisorId()));
        employee.setTeams(teamsService.getTeamsByIds(request.getTeamsIds()));
    }

    public void deleteEmployee(int id) {
        employeesRepository.delete(this.getEmployee(id));
    }

}
