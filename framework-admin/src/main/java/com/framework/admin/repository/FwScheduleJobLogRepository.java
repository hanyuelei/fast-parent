package com.framework.admin.repository;

import org.springframework.stereotype.Repository;

import com.framework.admin.entity.FwScheduleJobLogEntity;
import com.framework.common.base.repository.BaseRepository;

@Repository
public interface  FwScheduleJobLogRepository extends BaseRepository< FwScheduleJobLogEntity, Long> {

}
