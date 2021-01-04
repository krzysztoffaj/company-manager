package com.krzysztoffaj.companymanager.services;

import com.krzysztoffaj.companymanager.exceptions.notfound.EmployeeNotFoundException;
import com.krzysztoffaj.companymanager.model.domain.entities.Employee;
import com.krzysztoffaj.companymanager.model.domain.entities.Team;
import com.krzysztoffaj.companymanager.model.domain.enums.EmployeePosition;
import com.krzysztoffaj.companymanager.model.web.requests.CreateEmployeeRequest;
import com.krzysztoffaj.companymanager.model.web.requests.EditEmployeeRequest;
import com.krzysztoffaj.companymanager.repositories.EmployeesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Set<Employee> getEmployeesByIds(List<Integer> ids) {
        return employeesRepository.findByIdIn(ids);
    }

    public List<Employee> getAll() {
        return employeesRepository.findAll();
    }

    public void save(Employee employee) {
        employeesRepository.save(employee);
    }

    public void addTeamToManagingEmployees(Team newTeam) {
        Employee pm = getEmployee(newTeam.getPmId());
        Employee po = getEmployee(newTeam.getPoId());
        Employee scrummaster = getEmployee(newTeam.getScrummasterId());

        Employee[] employees = {pm, po, scrummaster};
        for (Employee employee : employees) {
            if (employee != null) {
                employee.getTeams().add(newTeam);
                save(employee);
            }
        }
    }

    private String[] getWordsExtractedFromQuery(String query) {
        //TODO Only alphanumeric query. Cannot be more than 3 words
        if (!query.matches(".*\\w.*")) {
            return new String[0];
        }
        return query.trim().split("\\s+");
    }

    public String prepareTypedQuery(String input) {
        //TODO cleanup
        final String[] words = getWordsExtractedFromQuery(input);
        if(words.length == 0) {
            return "FROM Employee";
        }

        StringBuilder query = new StringBuilder();

        query.append("FROM Employee WHERE ");
        for (int i = 0; i < words.length - 1; i++) {
            query.append("(first_name LIKE '%").append(words[i]).append("%' OR ");
            query.append("last_name LIKE '%").append(words[i]).append("%' OR ");
            query.append("position LIKE '%").append(words[i]).append("%') AND ");
        }
        query.append("(first_name LIKE '%").append(words[words.length - 1]).append("%' OR ");
        query.append("last_name LIKE '%").append(words[words.length - 1]).append("%' OR ");
        query.append("position LIKE '%").append(words[words.length - 1]).append("%')");

        return query.toString();
    }

    public Employee createEmployee(CreateEmployeeRequest request) {
        final Employee employee = new Employee();
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setSalary(request.getSalary());
        employee.setPosition(EmployeePosition.getStatusByName(request.getPosition()));
        employee.setSupervisor(this.getEmployee(request.getSupervisorId()));
        employee.setTeams(teamsService.getTeamsByIds(request.getTeamsIds()));
        return employee;
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
