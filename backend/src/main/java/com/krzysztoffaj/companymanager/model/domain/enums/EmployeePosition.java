package com.krzysztoffaj.companymanager.model.domain.enums;

import com.krzysztoffaj.companymanager.exceptions.notfound.EmployeePositionNotFound;
import lombok.Getter;

@Getter
public enum EmployeePosition {

    PRODUCT_OWNER("Product Owner"),
    PROJECT_MANAGER("Project Manager"),
    SCRUM_MASTER("Scrum Master"),
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
        throw new EmployeePositionNotFound();
    }


}
