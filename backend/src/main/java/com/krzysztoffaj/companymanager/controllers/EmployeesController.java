package com.krzysztoffaj.companymanager.controllers;

import com.krzysztoffaj.companymanager.mappers.EmployeesMapper;
import com.krzysztoffaj.companymanager.model.domain.entities.Employee;
import com.krzysztoffaj.companymanager.model.web.dtos.EmployeeDto;
import com.krzysztoffaj.companymanager.model.web.requests.CreateEmployeeRequest;
import com.krzysztoffaj.companymanager.model.web.requests.EditEmployeeRequest;
import com.krzysztoffaj.companymanager.services.EmployeesService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/employees")
public class EmployeesController {

    private final EmployeesService employeesService;
    private final EmployeesMapper employeesMapper;


//    @GetMapping
//    public List<EmployeeDto> setupEmployeesView() {
//        modelAndView.setViewName("employees");
//        return modelAndView;
//    }


    @GetMapping("/{id}")
    public EmployeeDto getEmployee(@PathVariable("id") int id) {
        return employeesMapper.mapToDto(employeesService.getEmployee(id));
    }

    @GetMapping("/employees/list-all")
    public List<Employee> getAllEmployees() {
        return employeesService.getAll();
    }

    @GetMapping("/employees/search")
    public List<Employee> search(@RequestParam("query") String query) {
        Session session = entityManager.unwrap(org.hibernate.Session.class);

        return session.createQuery(employeesService.prepareTypedQuery(query), Employee.class).getResultList();
    }

//    @GetMapping("/employees/add")
//    public ModelAndView setupAddEmployeeView() {
//        modelAndView.addObject("allEmployees", employeesService.getAll());
//        modelAndView.addObject("allTeams", teamsService.getAllTeams());1
//        modelAndView.setViewName("add-employee");
//        return modelAndView;
//    }

    @PostMapping("/employees")
    public ResponseEntity<?> createEmployee(@RequestBody @Valid CreateEmployeeRequest request) {
        final Employee employee = employeesService.createEmployee(request);

        final URI uri = MvcUriComponentsBuilder
                .fromMethodName(EmployeesController.class, "getEmployee", employee.getId())
                .buildAndExpand(employee.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

//    @GetMapping("/employees/edit/{id}")
//    public ModelAndView setupEditEmployeeView(@PathVariable("id") Integer id) {
//        try {
//            Employee editedEmployee = employeesService.getEmployee(id);
//            System.out.println(editedEmployee);
//            modelAndView.addObject("editedEmployee", editedEmployee);
//            modelAndView.addObject("allEmployees", employeesService.getAll());
//            modelAndView.addObject("allTeams", teamsService.getAllTeams());
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
        employeesService.editEmployee(id, request);
    }

    @DeleteMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable("id") int id) {
        employeesService.deleteEmployee(id);
    }

}