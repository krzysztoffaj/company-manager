package com.krzysztoffaj.companymanager.model.domain.entities;

import com.krzysztoffaj.companymanager.model.domain.enums.EmployeePosition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private int salary;

    @Enumerated(EnumType.STRING)
    private EmployeePosition position;

    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private Employee supervisor;

    @ManyToMany(mappedBy = "employees")
    private Set<Team> teams;

}
