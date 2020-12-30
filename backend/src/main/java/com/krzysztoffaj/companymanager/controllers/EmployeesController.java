package com.krzysztoffaj.companymanager.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.krzysztoffaj.companymanager.model.domain.entities.Employee;
import com.krzysztoffaj.companymanager.model.domain.entities.EmployeeWithTeamIds;
import com.krzysztoffaj.companymanager.infrastructure.View;
import com.krzysztoffaj.companymanager.model.web.dtos.EmployeeDto;
import com.krzysztoffaj.companymanager.model.web.requests.CreateEmployeeRequest;
import com.krzysztoffaj.companymanager.model.web.requests.EditEmployeeRequest;
import com.krzysztoffaj.companymanager.services.EmployeesService;
import com.krzysztoffaj.companymanager.services.TeamsService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/employees")
public class EmployeesController {

    private final EmployeesService employeeService;
    private final TeamsService teamService;


//    @GetMapping
//    public List<EmployeeDto> setupEmployeesView() {
//        modelAndView.setViewName("employees");
//        return modelAndView;
//    }


    @GetMapping("/{id}")
    public EmployeeDto getEmployee(@PathVariable("id") int id) {
        return employeeService.getEmployee(id);
    }

    @GetMapping("/employees/list-all")
    public List<Employee> getAllEmployees() {
        return employeeService.getAll();
    }

    @GetMapping("/employees/search")
    public List<Employee> search(@RequestParam("query") String query) {
        Session session = entityManager.unwrap(org.hibernate.Session.class);

        return session.createQuery(employeeService.prepareTypedQuery(query), Employee.class).getResultList();
    }

//    @GetMapping("/employees/add")
//    public ModelAndView setupAddEmployeeView() {
//        modelAndView.addObject("allEmployees", employeeService.getAll());
//        modelAndView.addObject("allTeams", teamService.getAllTeams());
//        modelAndView.setViewName("add-employee");
//        return modelAndView;
//    }

    @PostMapping("/employees")
    public ResponseEntity<?> createEmployee(@RequestBody @Valid CreateEmployeeRequest request) {
        final Employee employee = employeeService.createEmployee(request);

        final URI uri = MvcUriComponentsBuilder
                .fromMethodName(EmployeesController.class, "getEmployee", employee.getId())
                .buildAndExpand(employee.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

//    @GetMapping("/employees/edit/{id}")
//    public ModelAndView setupEditEmployeeView(@PathVariable("id") Integer id) {
//        try {
//            Employee editedEmployee = employeeService.getEmployee(id);
//            System.out.println(editedEmployee);
//            modelAndView.addObject("editedEmployee", editedEmployee);
//            modelAndView.addObject("allEmployees", employeeService.getAll());
//            modelAndView.addObject("allTeams", teamService.getAllTeams());
//            modelAndView.setViewName("edit-employee");
//        } catch (EntityNotFoundException e) {
//            modelAndView.setViewName("entity-not-found");
//        }
//        return modelAndView;
//    }

    @PutMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editEmployee(@PathVariable("id") int id,
                             @RequestBody @Valid EditEmployeeRequest request) {
        employeeService.editEmployee(id, request);
    }

    @DeleteMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable("id") int id) {
        employeeService.deleteEmployee(id);
    }

}