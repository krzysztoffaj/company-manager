package com.krzysztoffaj.companymanager.mappers;

import com.krzysztoffaj.companymanager.model.domain.entities.Employee;
import com.krzysztoffaj.companymanager.model.domain.entities.Team;
import com.krzysztoffaj.companymanager.model.web.dtos.EmployeeDto;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toList;

@Component
public class EmployeesMapper {

    public EmployeeDto mapToDto(Employee entity) {
        final EmployeeDto dto = new EmployeeDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setSalary(entity.getSalary());
        dto.setPosition(entity.getPosition().getName());
        dto.setSupervisorId(entity.getSupervisor().getId());
        dto.setTeamsIds(entity.getTeams().stream().map(Team::getId).collect(toList()));

        return dto;
    }

}
