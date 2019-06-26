package com.krzysztoffaj.companymanager.models;

import com.krzysztoffaj.companymanager.infrastructure.EmployeePosition;

public class Greeting {
    private String firstName;
    private String lastName;
    private EmployeePosition position;

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
}