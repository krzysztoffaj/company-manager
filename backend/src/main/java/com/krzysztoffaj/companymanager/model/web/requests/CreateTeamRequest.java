package com.krzysztoffaj.companymanager.model.web.requests;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
public class CreateTeamRequest {

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @Positive
    private int projectManagerId;

    @Positive
    private int productOwnerId;

    @Positive
    private int scrumMasterId;

    private List<Integer> membersIds;

}
