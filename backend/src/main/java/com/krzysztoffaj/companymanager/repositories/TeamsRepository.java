package com.krzysztoffaj.companymanager.repositories;

import com.krzysztoffaj.companymanager.model.domain.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface TeamsRepository extends JpaRepository<Team, Integer> {

    Set<Team> findByIdIn(List<Integer> ids);

    boolean existsByName(String name);

}
