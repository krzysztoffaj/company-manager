package com.krzysztoffaj.companymanager.repositories;

import com.krzysztoffaj.companymanager.entities.EntityId;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class GenericRepository<T extends EntityId> {

    @PersistenceContext
    private EntityManager entityManager;
}
