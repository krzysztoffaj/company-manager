package com.krzysztoffaj.companymanager.model.web.dtos;

import lombok.Data;

import java.util.List;

@Data
public class TeamDto {

    private int id;
    private String name;
    private EmployeeBasicDto projectManager;
    private EmployeeBasicDto productOwner;
    private EmployeeBasicDto scrumMaster;
    private List<EmployeeBasicDto> members;

}
