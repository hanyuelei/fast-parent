package com.framework.admin.service.impl;

import org.springframework.stereotype.Service;

import com.framework.admin.entity. FwScheduleJobLogEntity;
import com.framework.admin.repository.FwScheduleJobLogRepository;
import com.framework.admin.service. FwScheduleJobLogService;
import com.framework.common.base.service.impl.BaseServiceImpl;
@Service("fwScheduleJobLogService")
public class FwScheduleJobLogServiceImpl extends BaseServiceImpl<FwScheduleJobLogEntity, Long> implements FwScheduleJobLogService {

	private FwScheduleJobLogRepository fwScheduleJobLogRepository;
	public FwScheduleJobLogServiceImpl(FwScheduleJobLogRepository fwScheduleJobLogRepository) {
		super(fwScheduleJobLogRepository);
		// TODO Auto-generated constructor stub
		this.fwScheduleJobLogRepository=fwScheduleJobLogRepository;
	}


}
