package com.framework.admin.repository.impl;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.framework.admin.dao.FwDicDao;


public class FwDicRepositoryImpl  implements FwDicDao {

    @PersistenceContext
    private EntityManager entityManager;
}


