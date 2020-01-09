package com.framework.admin.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.framework.admin.dao.FwSysLogDao;

public class FwSysLogRepositoryImpl implements FwSysLogDao {
	
	@PersistenceContext//注入EntityManager，通过EntityManager来实现自定义方法
    private EntityManager entityManager;
}
