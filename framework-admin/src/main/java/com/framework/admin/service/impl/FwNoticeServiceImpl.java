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

import com.framework.admin.entity.FwNoticeEntity;
import com.framework.admin.repository.FwNoticeRepository;
import com.framework.admin.service.FwNoticeService;
import com.framework.common.base.service.impl.BaseServiceImpl;
import com.framework.common.util.result.Result;
@Service
public class FwNoticeServiceImpl extends BaseServiceImpl<FwNoticeEntity, Long> implements FwNoticeService {

	private FwNoticeRepository fwNoticeRepository;
	
	@Autowired
	public FwNoticeServiceImpl(FwNoticeRepository fwNoticeRepository) {
		super(fwNoticeRepository);
		// TODO Auto-generated constructor stub
		this.fwNoticeRepository=fwNoticeRepository;
	}

	@Override
	public FwNoticeEntity getNoticeByStatus(Integer status) {
		// TODO Auto-generated method stub
		List<Object> paramList =new ArrayList<Object>();
		String jpql="from FwNoticeEntity where status=?1 order by id desc";
		paramList.add(status);
		List<FwNoticeEntity> list=fwNoticeRepository.queryByJpql(jpql, paramList);
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public Result getNoticePage(Integer page, Integer limit, HttpServletRequest request) {
		Result r = Result.success();
    	List<Object> objList = new ArrayList<Object>();
        int paramPosition = 1;
        String jpql="from FwNoticeEntity where 1=1 ";
        String title=request.getParameter("title");
        if(StringUtils.isNotEmpty(title)) {
        	jpql+=" and title like ?"+paramPosition+"";
        	paramPosition+=1;
        	objList.add("%"+title+"%");
        }
        String content=request.getParameter("content");
        if(StringUtils.isNotEmpty(content)) {
        	jpql+=" and content like ?"+paramPosition+"";
        	paramPosition+=1;
        	objList.add("%"+content+"%");
        }
        String status=request.getParameter("status");
        if(StringUtils.isNotEmpty(status)) {
        	jpql+=" and status = ?"+paramPosition+"";
        	paramPosition+=1;
        	objList.add(Integer.valueOf(status));
        }
        jpql+="order by id desc";
        Pageable pageable = PageRequest.of(page, limit, null);
    	PageImpl pageData = (PageImpl)fwNoticeRepository.getPageByJpql(jpql, objList, pageable);
    	 r.setAny("count",pageData.getTotalElements());
         r.setAny("data",pageData.getContent());
        return r;
	}



}
