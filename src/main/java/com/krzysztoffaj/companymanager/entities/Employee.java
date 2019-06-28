package com.krzysztoffaj.companymanager.entities;

import com.krzysztoffaj.companymanager.infrastructure.competences.Competence;
import com.krzysztoffaj.companymanager.infrastructure.EmployeePosition;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee implements EntityId, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "position")
    @Enumerated(EnumType.STRING)
    private EmployeePosition position;

    @Column(name = "salary")
    private double salary;

    @Column(name = "supervisor_id")
    private int supervisorId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "employee_team",
            joinColumns = {@JoinColumn(name = "employee_id")},
            inverseJoinColumns = {@JoinColumn(name = "team_id")})
    private Set<Team> teams;

    @Transient
    private Competence competence;

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
        this.salary = salary;
    }

    public int getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(int supervisorId) {
        this.supervisorId = supervisorId;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public Competence getCompetence() {
        return competence;
    }

    public void setCompetence(Competence competence) {
        this.competence = competence;
    }

}