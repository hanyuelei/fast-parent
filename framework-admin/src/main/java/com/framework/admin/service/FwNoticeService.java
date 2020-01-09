package com.framework.admin.service;

import javax.servlet.http.HttpServletRequest;

import com.framework.admin.entity.FwNoticeEntity;
import com.framework.common.base.service.BaseService;
import com.framework.common.util.result.Result;

public interface FwNoticeService extends BaseService<FwNoticeEntity, Long> {

	public FwNoticeEntity getNoticeByStatus(Integer status);
	
	Result getNoticePage(Integer page,Integer limit,HttpServletRequest request);
}
