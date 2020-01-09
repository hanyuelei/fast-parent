package com.framework.admin.repository;

import org.springframework.stereotype.Repository;

import com.framework.admin.entity.FwScheduleJobEntity;
import com.framework.common.base.repository.BaseRepository;

@Repository
public interface  FwScheduleJobRepository extends BaseRepository< FwScheduleJobEntity, Long> {

}
