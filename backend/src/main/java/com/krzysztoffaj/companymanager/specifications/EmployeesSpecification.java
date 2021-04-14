package com.krzysztoffaj.companymanager.specifications;

import com.krzysztoffaj.companymanager.model.domain.entities.Employee;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

public class EmployeesSpecification {

    @NonNull
    public static Specification<Employee> hasValuesIterated(String[] values) {
        final Specification<Employee> specification = (Specification<Employee>) (root, criteriaQuery, criteriaBuilder) -> null;

        for (int i = 0; i < values.length; i++) {
            if (i + 1 < values.length) {
                specification.and(hasFirstOrLastName(values[i]).and(hasFirstOrLastName(values[i + 1])));
            } else if (i == values.length - 1) {
                specification.and(hasFirstOrLastName(values[i]));
            }
        }

        return specification;
    }


    @NonNull
    private static Specification<Employee> hasFirstName(String value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")),  "%" + value.toLowerCase() + "%");
    }

    @NonNull
    private static Specification<Employee> hasLastName(String value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" + value.toLowerCase() + "%");
    }

    @NonNull
    private static Specification<Employee> hasFirstOrLastName(String value) {
        return (root, query, criteriaBuilder) ->
                            hasFirstName(value)
                            .or(hasLastName(value))
                            .toPredicate(root, query, criteriaBuilder);
    }

}
