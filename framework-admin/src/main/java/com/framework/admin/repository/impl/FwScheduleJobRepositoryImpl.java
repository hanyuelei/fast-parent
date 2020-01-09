package com.framework.admin.repository.impl;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.framework.admin.dao.FwScheduleJobDao;


public class FwScheduleJobRepositoryImpl  implements FwScheduleJobDao {

    @PersistenceContext
    private EntityManager entityManager;
}


