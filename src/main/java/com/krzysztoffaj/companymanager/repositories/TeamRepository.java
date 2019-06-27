package com.krzysztoffaj.companymanager.repositories;

import com.krzysztoffaj.companymanager.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class TeamRepository implements JpaRepository<Team, Integer> {
}
