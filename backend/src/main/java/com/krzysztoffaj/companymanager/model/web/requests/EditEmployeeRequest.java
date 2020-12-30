package com.krzysztoffaj.companymanager.model.web.requests;

import lombok.Getter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
public class EditEmployeeRequest {

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
