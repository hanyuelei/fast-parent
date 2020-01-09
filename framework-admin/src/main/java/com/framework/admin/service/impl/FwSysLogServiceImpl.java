package com.framework.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.framework.admin.entity.FwSysLogEntity;
import com.framework.admin.repository.FwSysLogRepository;
import com.framework.admin.service.FwSysLogService;
import com.framework.common.base.service.impl.BaseServiceImpl;
import com.framework.common.util.result.Result;
@Service
public class FwSysLogServiceImpl extends BaseServiceImpl<FwSysLogEntity,Long> implements FwSysLogService {

	private FwSysLogRepository fwSysLogRepository;
	@Autowired
	public FwSysLogServiceImpl(FwSysLogRepository fwSysLogRepository) {
		super(fwSysLogRepository);
		// TODO Auto-generated constructor stub
		this.fwSysLogRepository=fwSysLogRepository;
	}
	@Override
	public Result getSysLogPage(Integer page, Integer limit, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Result r = Result.success();
    	List<Object> objList = new ArrayList<Object>();
        int paramPosition = 1;
        String jpql="from FwSysLogEntity where 1=1 ";
        String operation=request.getParameter("operation");
        if(StringUtils.isNotEmpty(operation)) {
        	jpql+=" and operation like ?"+paramPosition+"";
        	paramPosition+=1;
        	objList.add("%"+operation+"%");
        }
        String username=request.getParameter("username");
        if(StringUtils.isNotEmpty(username)) {
        	jpql+=" and username like ?"+paramPosition+"";
        	paramPosition+=1;
        	objList.add("%"+username+"%");
        }
        String nickName=request.getParameter("nickName");
        if(StringUtils.isNotEmpty(nickName)) {
        	jpql+=" and nickName like ?"+paramPosition+"";
        	paramPosition+=1;
        	objList.add("%"+nickName+"%");
        }
        jpql+="order by id desc";
        Pageable pageable = PageRequest.of(page, limit, null);
    	PageImpl pageData = (PageImpl)fwSysLogRepository.getPageByJpql(jpql, objList, pageable);
    	 r.setAny("count",pageData.getTotalElements());
         r.setAny("data",pageData.getContent());
        return r;
	}

}
