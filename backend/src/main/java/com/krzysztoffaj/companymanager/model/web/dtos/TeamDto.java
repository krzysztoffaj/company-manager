package com.krzysztoffaj.companymanager.model.web.dtos;

import lombok.Data;

import java.util.List;

@Data
public class TeamDto {

    private int id;
    private String name;
    private int projectManagerId;
    private int productOwnerId;
    private int scrumMasterId;
    private List<Integer> membersIds;

}
