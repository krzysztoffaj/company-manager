package com.krzysztoffaj.companymanager.controllers;

import com.krzysztoffaj.companymanager.entities.Employee;
import com.krzysztoffaj.companymanager.entities.Team;
import com.krzysztoffaj.companymanager.infrastructure.EmployeePosition;
import com.krzysztoffaj.companymanager.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public List<Employee> getAllEmployees() {
        return employeeService.getAll();
    }

    @GetMapping("/employees/search")
    public Set<Employee> search(@RequestParam("query") String query) {
        return employeeService.getUniqueResults(query);
    }

    @GetMapping("/employees/add")
    public ModelAndView setupAddEmployeeView() {
        modelAndView.setViewName("add-or-edit-employee");
        return modelAndView;
    }

    @PostMapping("/manageemployee/add")
    public Employee addNewEmployee(Employee employee) {
//        final Employee employee = employeeService.castQueryParamsToEmployeeObject(firstName, lastName, position, salary, supervisorId, teamIds);
        employeeService.save(employee);

        return employee;
    }

    @GetMapping("/employees/edit/{id}")
    public ModelAndView setupEditEmployeeView(@PathVariable("id") Integer id) {
//        try {
//            Employee editedEmployee = employeeService.get(employeeId);
//            System.out.println(editedEmployee);
//            model.addAttribute("employee", editedEmployee);
//        } catch (Exception e) {
//            return "entitynotfound";
//        }
        modelAndView.setViewName("add-or-edit-employee");
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