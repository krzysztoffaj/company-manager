package com.krzysztoffaj.companymanager.model.web.requests;

import lombok.Getter;

import javax.validation.constraints.*;
import java.util.List;

@Getter
public class CreateEmployeeRequest {

    @NotBlank
    @Size(min = 2, max = 50)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 50)
    private String lastName;

    @Positive
    @Digits(fraction = 2, integer = 15)
    private double salary;

    @NotBlank
    private String position;

    private int supervisorId;
    private List<Integer> teamsIds;

}
