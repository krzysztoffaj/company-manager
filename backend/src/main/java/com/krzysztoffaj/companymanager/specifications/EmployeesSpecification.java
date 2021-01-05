package com.krzysztoffaj.companymanager.specifications;

import com.krzysztoffaj.companymanager.model.domain.entities.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class EmployeesSpecification {

    private static EntityManager entityManager;

    @NonNull
    public static Specification<Employee> hasFirstName(String value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), value.toLowerCase());
    }

    @NonNull
    public static Specification<Employee> hasLastName(String value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), value.toLowerCase());
    }

    @NonNull
    public static Specification<Employee> hasPosition(String value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("position")), value.toLowerCase());
    }

    @NonNull
    public static Specification<Employee> hasFirstOrLastNameOrPosition(String value) {
        return (root, query, criteriaBuilder) ->
                        hasFirstName(value)
                        .or(hasLastName(value))
                        .or(hasPosition(value))
                        .toPredicate(root, query, criteriaBuilder);
    }

    @NonNull
    public static Specification<Employee> hasValuesIterated(List<String> values) {
        final List<Specification<Employee>> specifications = new ArrayList<>();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        for (int i = 0; i < values.size(); i++) {
            if (i < values.size() - 2) {
                specifications.add(hasFirstOrLastNameOrPosition(values.get(i)).and(hasFirstOrLastNameOrPosition(values.get(i + 1))));
            } else if (i == values.size() - 2) {
                specifications.add(hasFirstOrLastNameOrPosition(values.get(i)));
            }
        }
        return null;
    }

}
