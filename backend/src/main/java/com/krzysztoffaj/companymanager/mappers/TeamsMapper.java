package com.krzysztoffaj.companymanager.mappers;

import com.krzysztoffaj.companymanager.model.domain.entities.Team;
import com.krzysztoffaj.companymanager.model.web.dtos.TeamBasicDto;
import com.krzysztoffaj.companymanager.model.web.dtos.TeamDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class TeamsMapper {

    private final EmployeesMapper employeesMapper;


    public TeamDto mapToDto(Team entity) {
        final TeamDto dto = new TeamDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setProjectManager(employeesMapper.mapToDto(entity.getProjectManager()));
        dto.setProductOwner(employeesMapper.mapToDto(entity.getProductOwner()));
        dto.setScrumMaster(employeesMapper.mapToDto(entity.getScrumMaster()));
        dto.setMembers(employeesMapper.mapToDtos(entity.getMembers()));

        return dto;
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
