package com.krzysztoffaj.companymanager.controllers;

import com.krzysztoffaj.companymanager.entities.Employee;
import com.krzysztoffaj.companymanager.entities.Team;
import com.krzysztoffaj.companymanager.infrastructure.EmployeePosition;
import com.krzysztoffaj.companymanager.services.EmployeeService;
import com.krzysztoffaj.companymanager.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class ManageEmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private TeamService teamService;

    @GetMapping("/manageemployee/add")
    public String addNewEmployeeInit(Model model) {
        final List<Employee> allEmployees = employeeService.getAll();
        final List<Team> allTeams = teamService.getAll();
        model.addAttribute("allEmployees", allEmployees);
        model.addAttribute("allTeams", allTeams);

        Employee employee = null;
        model.addAttribute("employee", employee);

        return "manageemployee";
    }

    @GetMapping("/manageemployee/add/submit")
    public String addNewEmployeeSubmit(@RequestParam("firstName") String firstName,
                                       @RequestParam("lastName") String lastName,
                                       @RequestParam("position") EmployeePosition position,
                                       @RequestParam("salary") String salary,
                                       @RequestParam("supervisorId") String supervisorId,
                                       @RequestParam("teamIds") int[] teamIds) {
        final Employee employee = employeeService.castInputsToEmployeeObject(firstName, lastName, position, salary, supervisorId, teamIds);
        employeeService.save(employee);

        return "browseemployees";
    }

    @GetMapping("/manageemployee/edit/{employeeId}")
    public String editEmployeeInit(@PathVariable("employeeId") Integer employeeId,
                                   Model model) {
        final List<Employee> allEmployees = employeeService.getAll();
        final List<Team> allTeams = teamService.getAll();
        model.addAttribute("allEmployees", allEmployees);
        model.addAttribute("allTeams", allTeams);

        Employee editedEmployee = employeeService.get(employeeId);
        model.addAttribute("employee", editedEmployee);

        return "manageemployee";
    }

    @GetMapping("/manageemployee/edit/submit")
    public String editEmployeeSubmit(@RequestParam("employeeId") Integer employeeId,
                                     @RequestParam("position") EmployeePosition position,
                                     @RequestParam("salary") String salary,
                                     @RequestParam("supervisorId") String supervisorId,
                                     @RequestParam("teamIds") int[] teamIds) {
        employeeService.updateEmployeeInfo(employeeId, position, salary, supervisorId, teamIds);

        return "browseemployees";
    }
}