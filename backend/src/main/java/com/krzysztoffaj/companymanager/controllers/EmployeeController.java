package com.krzysztoffaj.companymanager.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.krzysztoffaj.companymanager.model.domain.entities.Employee;
import com.krzysztoffaj.companymanager.model.domain.entities.EmployeeWithTeamIds;
import com.krzysztoffaj.companymanager.infrastructure.View;
import com.krzysztoffaj.companymanager.services.EmployeeService;
import com.krzysztoffaj.companymanager.services.TeamService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private TeamService teamService;

    @Autowired
    private EntityManager entityManager;

    private ModelAndView modelAndView = new ModelAndView();

    @GetMapping("/employees")
    public ModelAndView setupEmployeesView() {
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
        modelAndView.addObject("allTeams", teamService.getAll());
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
            Employee editedEmployee = employeeService.get(id);
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
                                 @Valid @RequestBody EmployeeWithTeamIds employeeWithTeamIds) {
        return employeeService.saveEmployee(employeeWithTeamIds);
    }

    @DeleteMapping("/employees/delete/{id}")
    public void deleteEmployee(@PathVariable("id") Integer id) {
        employeeService.deleteEmployee(employeeService.get(id));
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public void handleValidationError(IllegalStateException ex) {
        System.out.println("Validation error occured!");
    }
}