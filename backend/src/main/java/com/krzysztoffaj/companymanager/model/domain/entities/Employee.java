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
    @JsonView(View.BasicInfo.class)
    private int id;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]*$")
    @Size(min = 2, max = 50)
    @JsonView(View.BasicInfo.class)
    private String firstName;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]*$")
    @Size(min = 2, max = 50)
    @JsonView(View.BasicInfo.class)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @JsonView(View.BasicInfo.class)
    private EmployeePosition position;

    @NotNull
    @Digits(fraction = 2, integer = 15)
    @JsonView(View.BasicInfo.class)
    private double salary;

    @Column(name = "supervisor_id")
    @JsonView(View.BasicInfo.class)
    private Integer supervisorId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "employee_team",
            joinColumns = {@JoinColumn(name = "employee_id")},
            inverseJoinColumns = {@JoinColumn(name = "team_id")})
    @JsonView(View.DetailedEmployeesInfo.class)
    private Set<Team> teams;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public EmployeePosition getPosition() {
        return position;
    }

    public void setPosition(EmployeePosition position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary < 0) {
            throw new InvalidSalaryException();
        }
        this.salary = salary;
    }

    public Integer getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Integer supervisorId) {
        this.supervisorId = supervisorId;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

//    public Competence getCompetence() {
//        return competence;
//    }
//
//    public void setCompetence(Competence competence) {
//        this.competence = competence;
//    }
}