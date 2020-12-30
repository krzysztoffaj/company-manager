package com.krzysztoffaj.companymanager.model.domain.entities;

import com.fasterxml.jackson.annotation.*;
import com.krzysztoffaj.companymanager.exceptions.badrequest.InvalidSalaryException;
import com.krzysztoffaj.companymanager.model.domain.enums.EmployeePosition;
import com.krzysztoffaj.companymanager.infrastructure.View;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]*$")
    @Size(min = 2, max = 50)
    private String firstName;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]*$")
    @Size(min = 2, max = 50)
    private String lastName;

    @Enumerated(EnumType.STRING)
    private EmployeePosition position;

    @NotNull
    @Digits(fraction = 2, integer = 15)
    private double salary;

    @Column(name = "supervisor_id")
    private Integer supervisorId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "employee_team",
            joinColumns = {@JoinColumn(name = "employee_id")},
            inverseJoinColumns = {@JoinColumn(name = "team_id")})
    private Set<Team> teams;

//    public void setSalary(double salary) {
//        if (salary < 0) {
//            throw new InvalidSalaryException();
//        }
//        this.salary = salary;
//    }

}