package com.krzysztoffaj.companymanager.model.web.dtos;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeDto {

    private int id;
    private String firstName;
    private String lastName;
    private int salary;
    private String position;
    private EmployeeBasicDto supervisor;
    private List<TeamBasicDto> teams;

}
