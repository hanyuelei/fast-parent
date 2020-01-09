package com.framework.admin.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.framework.admin.dao.FwRoleDao;
import com.framework.admin.entity.FwRoleEntity;
import com.framework.common.base.repository.BaseRepository;
@Repository
public interface FwRoleRepository extends BaseRepository<FwRoleEntity, Long> , FwRoleDao {

	public List<FwRoleEntity> getRolesBycreateUserId(Long createUserId);
}
