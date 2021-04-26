package com.krzysztoffaj.companymanager.specifications;

import com.krzysztoffaj.companymanager.model.domain.entities.Employee;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import javax.persistence.criteria.Predicate;
import java.util.List;

public class EmployeesSpecification {

    @NonNull
    public static Specification<Employee> hasValuesIterated(List<String> values) {
        return (root, query, builder) -> builder.and(values.stream()
                                                           .map(value -> hasFirstOrLastName(value).toPredicate(root, query, builder))
                                                           .toArray(Predicate[]::new));
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
