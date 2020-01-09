package com.framework.admin.repository.impl;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.framework.admin.dao.FwImageDao;


public class FwImageRepositoryImpl  implements FwImageDao {

    @PersistenceContext
    private EntityManager entityManager;
}


