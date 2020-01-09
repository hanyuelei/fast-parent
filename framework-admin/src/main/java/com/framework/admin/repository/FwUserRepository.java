package com.framework.admin.repository;

import org.springframework.stereotype.Repository;

import com.framework.admin.dao.FwUserDao;
import com.framework.admin.entity.FwUserEntity;
import com.framework.common.base.repository.BaseRepository;
@Repository
public interface FwUserRepository extends BaseRepository<FwUserEntity, Long> ,FwUserDao  {

	FwUserEntity findByUserNameEquals(String userName);
}
