package com.framework.admin.repository.impl;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.framework.admin.dao.FwScheduleJobLogDao;


public class FwScheduleJobLogRepositoryImpl  implements FwScheduleJobLogDao {

    @PersistenceContext
    private EntityManager entityManager;
}


