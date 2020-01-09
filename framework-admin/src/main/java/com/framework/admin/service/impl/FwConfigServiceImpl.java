package com.framework.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.framework.admin.entity.FwConfigEntity;
import com.framework.admin.repository.FwConfigRepository;
import com.framework.admin.service.FwConfigService;
import com.framework.common.base.service.impl.BaseServiceImpl;
import com.framework.common.util.result.Result;
@Service
public class FwConfigServiceImpl extends BaseServiceImpl<FwConfigEntity, Long> implements FwConfigService {

	private FwConfigRepository fwConfigRepository;
	public FwConfigServiceImpl(FwConfigRepository fwConfigRepository) {
		super(fwConfigRepository);
		// TODO Auto-generated constructor stub
		this.fwConfigRepository=fwConfigRepository;
	}
	@Override
	public boolean checkKey(String key, Long id) {
		// TODO Auto-generated method stub
		List<Object> paramList =new ArrayList<Object>();
		String jpql="from FwConfigEntity where paramKey = ?1";
	
		paramList.add(key);
		if(null!=id) {
			jpql+=" and id <> ?2";
			paramList.add(id);
		}
	
		List<FwConfigEntity>  list= fwConfigRepository.queryByJpql(jpql, paramList);
		if(list.size()>0) {
			return false;
		}
		return true;
	}
	@Override
	public Result getConfigPage(Integer page, Integer limit, HttpServletRequest request) {
		// TODO Auto-generated method stub
	 	List<Object> objList = new ArrayList<Object>();
        int paramPosition = 1;
        String jpql="from FwConfigEntity where 1=1 ";
        String paramKey=request.getParameter("paramKey");
        if(StringUtils.isNotEmpty(paramKey)) {
        	jpql+=" and paramKey like ?"+paramPosition+"";
        	paramPosition+=1;
        	objList.add("%"+paramKey+"%");
        }
        String paramValue=request.getParameter("paramValue");
        if(StringUtils.isNotEmpty(paramValue)) {
        	jpql+=" and paramValue like ?"+paramPosition+"";
        	paramPosition+=1;
        	objList.add("%"+paramValue+"%");
        }
        String remark=request.getParameter("remark");
        if(StringUtils.isNotEmpty(remark)) {
        	jpql+=" and remark like ?"+paramPosition+"";
        	paramPosition+=1;
        	objList.add("%"+remark+"%");
        }
        jpql+="order by id desc";
        Pageable pageable = PageRequest.of(page, limit, null);
    	PageImpl pageData = (PageImpl)fwConfigRepository.getPageByJpql(jpql, objList, pageable);
 		return Result.success().setAny("count", pageData.getTotalElements()).setAny("data", pageData.getContent());
	}


}
