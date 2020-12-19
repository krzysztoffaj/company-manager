package com.krzysztoffaj.companymanager.repositories;

import com.krzysztoffaj.companymanager.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TeamRepository extends JpaRepository<Team, Integer> {
}
