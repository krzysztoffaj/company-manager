package com.krzysztoffaj.companymanager.mappers;

import com.krzysztoffaj.companymanager.model.domain.entities.Employee;
import com.krzysztoffaj.companymanager.model.web.dtos.EmployeeBasicDto;
import com.krzysztoffaj.companymanager.model.web.dtos.EmployeeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Component
@RequiredArgsConstructor
public class EmployeesMapper {

    private final TeamsMapper teamsMapper;

    public EmployeeDto mapToDto(Employee entity) {
        final EmployeeDto dto = new EmployeeDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setSalary(entity.getSalary());
        dto.setPosition(entity.getPosition().getName());
        dto.setSupervisor(this.mapToBasicDto(entity.getSupervisor()));
        dto.setTeams(teamsMapper.mapToBasicDtos(entity.getTeams()));

        return dto;
    }

    public List<EmployeeDto> mapToDtos(Set<Employee> employees) {
        return employees.stream()
                        .map(this::mapToDto)
                        .sorted(comparing(EmployeeDto::getLastName))
                        .collect(Collectors.toList());
    }


    private EmployeeBasicDto mapToBasicDto(Employee entity) {
        final EmployeeBasicDto dto = new EmployeeBasicDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());

        return dto;
    }

}
