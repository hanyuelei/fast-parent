package com.framework.admin.service;

import javax.servlet.http.HttpServletRequest;

import com.framework.admin.entity.FwImageEntity;
import com.framework.common.base.service.BaseService;
import com.framework.common.util.result.Result;

public interface FwImageService extends BaseService<FwImageEntity, Long> {

	Result getImagePage(Integer page,Integer limit,HttpServletRequest request);
}


