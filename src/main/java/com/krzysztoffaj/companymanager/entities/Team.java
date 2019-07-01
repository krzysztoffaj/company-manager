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
    private int pmId;

    @Column(name = "po_id")
    private int poId;

    @Column(name = "scrummaster_id")
    private int scrummasterId;

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

    public int getPmId() {
        return pmId;
    }

    public void setPmId(int pmId) {
        this.pmId = pmId;
    }

    public int getPoId() {
        return poId;
    }

    public void setPoId(int poId) {
        this.poId = poId;
    }

    public int getScrummasterId() {
        return scrummasterId;
    }

    public void setScrummasterId(int scrummasterId) {
        this.scrummasterId = scrummasterId;
    }

    public void setMembers(Set<Employee> members) {
        this.members = members;
    }
}
