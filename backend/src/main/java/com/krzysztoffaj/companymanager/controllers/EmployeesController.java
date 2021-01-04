package com.krzysztoffaj.companymanager.controllers;

import com.krzysztoffaj.companymanager.mappers.EmployeesMapper;
import com.krzysztoffaj.companymanager.model.domain.entities.Employee;
import com.krzysztoffaj.companymanager.model.domain.enums.EmployeePosition;
import com.krzysztoffaj.companymanager.model.web.dtos.EmployeeDto;
import com.krzysztoffaj.companymanager.model.web.requests.CreateEmployeeRequest;
import com.krzysztoffaj.companymanager.model.web.requests.EditEmployeeRequest;
import com.krzysztoffaj.companymanager.services.EmployeesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashSet;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/employees")
public class EmployeesController {

    private final EmployeesService employeesService;
    private final EmployeesMapper employeesMapper;


    @GetMapping("/{id}")
    public EmployeeDto getEmployee(@PathVariable("id") int id) {
        return employeesMapper.mapToDto(employeesService.getEmployee(id));
    }

    @GetMapping
    public List<EmployeeDto> getAllEmployees() {
        return employeesMapper.mapToDtos(new HashSet<>(employeesService.getAll()));
    }

    @GetMapping("/filtered")
    public List<EmployeeDto> getEmployeesByInput(@RequestParam("input") String input) {
        return employeesMapper.mapToDtos(new HashSet<>(employeesService.getEmployeesByInput(input)));
    }

    @GetMapping("/product-owners")
    public List<EmployeeDto> getProductOwners() {
        return employeesMapper.mapToDtos(new HashSet<>(employeesService.getEmployeesByPosition(EmployeePosition.PRODUCT_OWNER)));
    }

    @GetMapping("/project-managers")
    public List<EmployeeDto> getProjectManagers() {
        return employeesMapper.mapToDtos(new HashSet<>(employeesService.getEmployeesByPosition(EmployeePosition.PROJECT_MANAGER)));
    }

    @GetMapping("/scrum-masters")
    public List<EmployeeDto> getScrumMasters() {
        return employeesMapper.mapToDtos(new HashSet<>(employeesService.getEmployeesByPosition(EmployeePosition.SCRUM_MASTER)));
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody @Valid CreateEmployeeRequest request) {
        final Employee employee = employeesService.createEmployee(request);

        final URI uri = MvcUriComponentsBuilder
                .fromMethodName(EmployeesController.class, "getEmployee", employee.getId())
                .buildAndExpand(employee.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editEmployee(@PathVariable("id") int id,
                             @RequestBody @Valid EditEmployeeRequest request) {
        employeesService.editEmployee(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable("id") int id) {
        employeesService.deleteEmployee(id);
    }

}