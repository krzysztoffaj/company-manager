package com.krzysztoffaj.companymanager.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "team")
public class Team implements EntityId, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "pm_id")
    private Integer pmId;

    @Column(name = "po_id")
    private Integer poId;

    @Column(name = "scrummaster_id")
    private Integer scrummasterId;

    @ManyToMany(mappedBy = "teams")
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
