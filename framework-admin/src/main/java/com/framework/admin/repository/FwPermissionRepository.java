package com.framework.admin.repository;

import org.springframework.stereotype.Repository;

import com.framework.admin.dao.FwPermissionDao;
import com.framework.admin.entity.FwPermissionEntity;
import com.framework.common.base.repository.BaseRepository;

@Repository
public interface FwPermissionRepository extends BaseRepository<FwPermissionEntity, Long>, FwPermissionDao {

}
