package com.framework.admin.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.framework.admin.entity.FwSysLogEntity;
import com.framework.common.base.service.BaseService;
import com.framework.common.util.result.Result;
@Service
public interface FwSysLogService extends BaseService<FwSysLogEntity, Long> {

	Result getSysLogPage(Integer page,Integer limit,HttpServletRequest request);
}
