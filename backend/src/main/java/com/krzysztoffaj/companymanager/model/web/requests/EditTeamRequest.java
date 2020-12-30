package com.krzysztoffaj.companymanager.model.web.requests;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class EditTeamRequest {

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    private int projectManagerId;
    private int productOwnerId;
    private int scrumMasterId;

}
