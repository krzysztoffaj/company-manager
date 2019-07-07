package com.krzysztoffaj.companymanager.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.krzysztoffaj.companymanager.entities.Employee;
import com.krzysztoffaj.companymanager.entities.EmployeeWithTeams;
import com.krzysztoffaj.companymanager.entities.Team;
import com.krzysztoffaj.companymanager.infrastructure.View;
import com.krzysztoffaj.companymanager.services.EmployeeService;
import com.krzysztoffaj.companymanager.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private TeamService teamService;

    private ModelAndView modelAndView = new ModelAndView();

    @GetMapping("/employees")
    public ModelAndView setupEmployeesView() {
        modelAndView.setViewName("employees");
        return modelAndView;
    }

//    @GetMapping("/employees/get-by-id/{id}")
//    @JsonView(View.BasicInfo.class)
//    public Employee getEmployeeById(@PathVariable("id") Integer id) {
//        return employeeService.get(id);
//    }
//
    @GetMapping("/employees/get-by-id/{id}")
    @JsonView(View.BasicInfo.class)
    public List<Employee> getEmployeeById(@PathVariable("id") Integer id) {
        return Collections.singletonList(employeeService.get(id));
    }

    @GetMapping("/employees/list-all")
    @JsonView(View.DetailedEmployeesInfo.class)
    public List<Employee> getAllEmployees() {
        return employeeService.getAll();
    }

    @GetMapping("/employees/search")
    @JsonView(View.DetailedEmployeesInfo.class)
    public Set<Employee> search(@RequestParam("query") String query) {
        return employeeService.handleSearching(query);
    }

    @GetMapping("/employees/add")
    public ModelAndView setupAddEmployeeView() {
        modelAndView.addObject("allEmployees", employeeService.getAll());
        modelAndView.addObject("allTeams", teamService.getAll());
        modelAndView.setViewName("add-employee");
        return modelAndView;
    }

    @PostMapping("/employees/add")
    public Employee addNewEmployee(@RequestBody Employee employee) {
        System.out.println(employee.getFirstName());
        employeeService.save(employee);
        return employee;
    }

    @GetMapping("/employees/edit/{id}")
    public ModelAndView setupEditEmployeeView(@PathVariable("id") Integer id) {
        try {
            Employee editedEmployee = employeeService.get(id);
//            Employee editedEmployee = getEmployeeById(id);
            System.out.println(editedEmployee);
            modelAndView.addObject("editedEmployee", editedEmployee);
            modelAndView.addObject("allEmployees", employeeService.getAll());
            modelAndView.addObject("allTeams", teamService.getAll());
            modelAndView.setViewName("edit-employee");
        } catch (EntityNotFoundException e) {
            modelAndView.setViewName("entity-not-found");
        }
        return modelAndView;
    }

    @PutMapping("/employees/edit/{id}")
    @JsonView(View.BasicInfo.class)
    public Employee editEmployee(@PathVariable("id") Integer id,
                                 @RequestBody EmployeeWithTeams employeeWithTeams) {
        System.out.println(employeeWithTeams.toString());
//        employeeService.updateEmployeeInfo(id, position, salary, supervisorId, teamIds);
        final Employee employee = employeeWithTeams.getEmployee();
        Set<Team> teams = new HashSet<>();
        for (int teamId : employeeWithTeams.getTeams()) {
            teams.add(teamService.get(teamId));
        }
        employee.setTeams(teams);

        employeeService.save(employee);

        return employee;
    }
}