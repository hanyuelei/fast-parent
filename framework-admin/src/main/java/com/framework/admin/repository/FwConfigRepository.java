package com.framework.admin.repository;

import org.springframework.stereotype.Repository;

import com.framework.admin.entity.FwConfigEntity;
import com.framework.common.base.repository.BaseRepository;
@Repository
public interface FwConfigRepository extends BaseRepository<FwConfigEntity, Long> {

}
