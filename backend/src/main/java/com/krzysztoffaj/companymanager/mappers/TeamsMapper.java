package com.krzysztoffaj.companymanager.mappers;

import com.krzysztoffaj.companymanager.model.domain.entities.Team;
import com.krzysztoffaj.companymanager.model.web.dtos.TeamBasicDto;
import com.krzysztoffaj.companymanager.model.web.dtos.TeamDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class TeamsMapper {

    @Lazy
    private final EmployeesMapper employeesMapper;


    public TeamDto mapToDto(Team entity) {
        final TeamDto dto = new TeamDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setProjectManager(employeesMapper.mapToBasicDto(entity.getProjectManager()));
        dto.setProductOwner(employeesMapper.mapToBasicDto(entity.getProductOwner()));
        dto.setScrumMaster(employeesMapper.mapToBasicDto(entity.getScrumMaster()));
        dto.setEmployees(employeesMapper.mapToBasicDtos(entity.getEmployees()));

        return dto;
    }

    public List<TeamDto> mapToDtos(List<Team> entities) {
        return entities.stream()
                       .map(this::mapToDto)
                       .sorted(comparing(TeamDto::getName))
                       .collect(toList());
    }

    public TeamBasicDto mapToBasicDto(Team entity) {
        final TeamBasicDto dto = new TeamBasicDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());

        return dto;
    }

    public List<TeamBasicDto> mapToBasicDtos(Set<Team> entities) {
        return entities.stream()
                       .map(this::mapToBasicDto)
                       .sorted(comparing(TeamBasicDto::getName))
                       .collect(toList());
    }

}
