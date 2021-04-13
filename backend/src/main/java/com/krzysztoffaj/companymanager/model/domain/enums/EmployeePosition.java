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
        String fixedName = name;
        if (fixedName.contains(" ")) {
            fixedName = name.replace(" ", "_").toUpperCase();
        }

        for (EmployeePosition position : values()) {
            if (String.valueOf(position).equals(fixedName)) {
                return position;
            }
        }
        throw new EmployeePositionNotFound();
    }


}
