package com.krzysztoffaj.companymanager.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.krzysztoffaj.companymanager.model.domain.entities.Employee;
import com.krzysztoffaj.companymanager.model.domain.entities.EmployeeWithTeamIds;
import com.krzysztoffaj.companymanager.infrastructure.View;
import com.krzysztoffaj.companymanager.model.web.dtos.EmployeeDto;
import com.krzysztoffaj.companymanager.services.EmployeesService;
import com.krzysztoffaj.companymanager.services.TeamsService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/employees")
public class EmployeesController {

    private final EmployeesService employeeService;
    private final TeamsService teamService;


    @GetMapping
    public List<EmployeeDto> setupEmployeesView() {
        modelAndView.setViewName("employees");
        return modelAndView;
    }

    @GetMapping("/employees/list-all")
    @JsonView(View.DetailedEmployeesInfo.class)
    public List<Employee> getAllEmployees() {
        return employeeService.getAll();
    }

    @GetMapping("/employees/search")
    @JsonView(View.DetailedEmployeesInfo.class)
    public List<Employee> search(@RequestParam("query") String query) {
        Session session = entityManager.unwrap(org.hibernate.Session.class);

        return session.createQuery(employeeService.prepareTypedQuery(query), Employee.class).getResultList();
    }

    @GetMapping("/employees/add")
    public ModelAndView setupAddEmployeeView() {
        modelAndView.addObject("allEmployees", employeeService.getAll());
        modelAndView.addObject("allTeams", teamService.getAllTeams());
        modelAndView.setViewName("add-employee");
        return modelAndView;
    }

    @PostMapping("/employees/add")
    @JsonView(View.BasicInfo.class)
    public Employee addNewEmployee(@Valid @RequestBody EmployeeWithTeamIds employeeWithTeamIds) {
        return employeeService.saveEmployee(employeeWithTeamIds);
    }

    @GetMapping("/employees/edit/{id}")
    public ModelAndView setupEditEmployeeView(@PathVariable("id") Integer id) {
        try {
            Employee editedEmployee = employeeService.getEmployee(id);
            System.out.println(editedEmployee);
            modelAndView.addObject("editedEmployee", editedEmployee);
            modelAndView.addObject("allEmployees", employeeService.getAll());
            modelAndView.addObject("allTeams", teamService.getAllTeams());
            modelAndView.setViewName("edit-employee");
        } catch (EntityNotFoundException e) {
            modelAndView.setViewName("entity-not-found");
        }
        return modelAndView;
    }

    @PutMapping("/employees/edit/{id}")
    @JsonView(View.BasicInfo.class)
    public Employee editEmployee(@PathVariable("id") Integer id,
                                 @Valid @RequestBody EmployeeWithTeamIds employeeWithTeamIds) {
        return employeeService.saveEmployee(employeeWithTeamIds);
    }

    @DeleteMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable("id") int id) {
        employeeService.deleteEmployee(id);
    }

}