package com.krzysztoffaj.companymanager.model.domain.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.krzysztoffaj.companymanager.infrastructure.View;

import java.util.List;

public class EmployeeWithTeamIds {
    @JsonView(View.BasicInfo.class)
    private Employee employee;
    private List<Integer> teams;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Integer> getTeams() {
        return teams;
    }

    public void setTeams(List<Integer> teams) {
        this.teams = teams;
    }
}
