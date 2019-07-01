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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AddNewEmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private TeamService teamService;

    @GetMapping("/addnewemployee")
    public String addNewEmployeeInit(Model model) {
        final List<Employee> allEmployees = employeeService.getAll();
        final List<Team> allTeams = teamService.getAll();
        model.addAttribute("allEmployees", allEmployees);
        model.addAttribute("allTeams", allTeams);

        return "addnewemployee";
    }

    @GetMapping("/addnewemployeesubmit")
    public String addNewEmployeeSubmit(@RequestParam("firstName") String firstName,
                                       @RequestParam("lastName") String lastName,
                                       @RequestParam("position") EmployeePosition position,
                                       @RequestParam("salary") String salary,
                                       @RequestParam("supervisorId") int supervisorId,
                                       @RequestParam("teams") int[] teams) {
        final Employee employee = employeeService.castInputsToEmployeeObject(firstName, lastName, position, salary, supervisorId, teams);
        employeeService.save(employee);

        return "search";
    }
}
