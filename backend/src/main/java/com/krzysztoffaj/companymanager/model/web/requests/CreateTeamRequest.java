package com.krzysztoffaj.companymanager.model.web.requests;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
public class CreateTeamRequest {


    @NotBlank
    @Size(min = 2, max = 100)
    private String name;


}
