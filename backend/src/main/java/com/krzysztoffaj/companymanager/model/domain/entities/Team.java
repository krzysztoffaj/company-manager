package com.krzysztoffaj.companymanager.model.domain.entities;

import com.fasterxml.jackson.annotation.*;
import com.krzysztoffaj.companymanager.infrastructure.View;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "teams")
public class Team {

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

}
