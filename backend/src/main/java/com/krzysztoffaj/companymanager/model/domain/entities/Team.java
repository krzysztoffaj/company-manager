package com.krzysztoffaj.companymanager.model.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "teams",
        indexes = @Index(columnList = "name", unique = true))
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "project_manager_id")
    private Employee projectManager;

    @ManyToOne
    @JoinColumn(name = "product_owner_id")
    private Employee productOwner;

    @ManyToOne
    @JoinColumn(name = "scrummaster_id")
    private Employee scrumMaster;

    @ManyToMany(mappedBy = "teams")
    private Set<Employee> members;

}
