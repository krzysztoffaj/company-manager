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
    private int id;

    private String name;

    @Column(name = "pm_id")
    private Employee projectManager;

    @Column(name = "po_id")
    private Employee productOwner;

    @OneToMany
    @JoinColumn(name = "scrummaster_id")
    private Employee scrumMaster;

    @ManyToMany(mappedBy = "teams")
    private Set<Employee> members;

}
