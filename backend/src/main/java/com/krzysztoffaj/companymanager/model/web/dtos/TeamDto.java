package com.krzysztoffaj.companymanager.model.web.dtos;

import lombok.Data;

import java.util.List;

@Data
public class TeamDto {

    private int id;
    private String name;
    private EmployeeDto projectManager;
    private EmployeeDto productOwner;
    private EmployeeDto scrumMaster;
    private List<EmployeeDto> members;

}
