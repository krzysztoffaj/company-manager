package com.krzysztoffaj.companymanager.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.krzysztoffaj.companymanager.entities.Employee;
import com.krzysztoffaj.companymanager.infrastructure.View;
import com.krzysztoffaj.companymanager.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

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
    public Set<Employee> search(@RequestParam("query") String query) {
        return employeeService.handleSearching(query);
    }

    @GetMapping("/employees/add")
    public ModelAndView setupAddEmployeeView() {
        modelAndView.setViewName("add-or-edit-employee");
        return modelAndView;
    }

    @PostMapping("/employees/add")
    public Employee addNewEmployee(Employee employee) {
//        final Employee employee = employeeService.castQueryParamsToEmployeeObject(firstName, lastName, position, salary, supervisorId, teamIds);
        employeeService.save(employee);

        return employee;
    }

    @GetMapping("/employees/edit/{id}")
    public ModelAndView setupEditEmployeeView(@PathVariable("id") Integer id) {
        try {
            Employee editedEmployee = employeeService.get(id);
            System.out.println(editedEmployee);
            modelAndView.setViewName("add-or-edit-employee");
        } catch (Exception e) {
            modelAndView.setViewName("entity-not-found");
        }
        return modelAndView;
    }

    @PutMapping("/employees/edit/{id}")
    public Employee editEmployee(@PathVariable("id") Integer id,
                                 Employee employee) {
//        employeeService.updateEmployeeInfo(id, position, salary, supervisorId, teamIds);
        employeeService.save(employee);

        return employee;
    }
}