package com.krzysztoffaj.companymanager.entities;

import com.fasterxml.jackson.annotation.*;
import com.krzysztoffaj.companymanager.infrastructure.View;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "team")
public class Team implements EntityId, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    @JsonView(View.BasicInfo.class)
    private int id;

    @Column(name = "name")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]*$")
    @Size(min = 2, max = 100)
    @JsonView(View.BasicInfo.class)
    private String name;

    @Column(name = "pm_id")
    @JsonView(View.DetailedTeamsInfo.class)
    private Integer pmId;

    @Column(name = "po_id")
    @JsonView(View.DetailedTeamsInfo.class)
    private Integer poId;

    @Column(name = "scrummaster_id")
    @JsonView(View.DetailedTeamsInfo.class)
    private Integer scrummasterId;

    @ManyToMany(mappedBy = "teams")
    @JsonView(View.DetailedTeamsInfo.class)
    private Set<Employee> members;

    public Team() {
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getMembers() {
        return members;
    }

    public Integer getPmId() {
        return pmId;
    }

    public void setPmId(Integer pmId) {
        this.pmId = pmId;
    }

    public Integer getPoId() {
        return poId;
    }

    public void setPoId(Integer poId) {
        this.poId = poId;
    }

    public Integer getScrummasterId() {
        return scrummasterId;
    }

    public void setScrummasterId(Integer scrummasterId) {
        this.scrummasterId = scrummasterId;
    }

    public void setMembers(Set<Employee> members) {
        this.members = members;
    }
}
