package com.krzysztoffaj.companymanager.model.domain.enums;

import lombok.Getter;

@Getter
public enum EmployeePosition {

    PROJECT_MANAGER("Project manager"),
    PRODUCT_OWNER("Product owner"),
    SCRUM_MASTER("Scrum master"),
    DEVELOPER("Developer"),
    DEVOPS_ENGINEER("DevOps Engineer"),
    TESTER("Tester"),
    ANALYST("Analyst");

    private final String name;

    EmployeePosition(String name) {
        this.name = name;
    }

    public static EmployeePosition getStatusByName(String name) {
        for (EmployeePosition position : values()) {
            if (position.name.equals(name)) {
                return position;
            }
        }
        throw new IllegalArgumentException(name);
    }


}
