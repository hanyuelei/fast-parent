package com.framework.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.framework.admin.entity. FwDicEntity;
import com.framework.admin.repository.FwDicRepository;
import com.framework.admin.service.FwDicService;
import com.framework.common.base.service.impl.BaseServiceImpl;
import com.framework.common.util.result.Result;
@Service("fwDicService")
public class FwDicServiceImpl extends BaseServiceImpl<FwDicEntity, Long> implements FwDicService {

	private FwDicRepository fwDicRepository;
	public FwDicServiceImpl(FwDicRepository fwDicRepository) {
		super(fwDicRepository);
		// TODO Auto-generated constructor stub
		this.fwDicRepository=fwDicRepository;
	}
	@Override
	public Result getDicPage(Integer page, Integer limit, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Result r = Result.success();
    	List<Object> objList = new ArrayList<Object>();
        int paramPosition = 1;
        String jpql="from FwDicEntity where 1=1 ";
        String name=request.getParameter("name");
        if(StringUtils.isNotEmpty(name)) {
        	jpql+=" and name like ?"+paramPosition+"";
        	paramPosition+=1;
        	objList.add("%"+name+"%");
        }
        String title=request.getParameter("title");
        if(StringUtils.isNotEmpty(title)) {
        	jpql+=" and title like ?"+paramPosition+"";
        	paramPosition+=1;
        	objList.add("%"+title+"%");
        }
        jpql+="order by id desc";
        Pageable pageable = PageRequest.of(page, limit, null);
    	PageImpl pageData = (PageImpl)fwDicRepository.getPageByJpql(jpql, objList, pageable);
    	r.setAny("count",pageData.getTotalElements());
        r.setAny("data",pageData.getContent());
        return r;
	}
	@Override
	public boolean checkName(String name, Long id) {
		// TODO Auto-generated method stub
		List<Object> paramList =new ArrayList<Object>();
		String jpql="from FwDicEntity where name = ?1";
	
		paramList.add(name);
		if(null!=id) {
			jpql+=" and id <> ?2";
			paramList.add(id);
		}
		List<FwDicEntity>  list= fwDicRepository.queryByJpql(jpql, paramList);
		if(list.size()>0) {
			return false;
		}
		return true;
	}
	@Override
	public FwDicEntity getModelByName(String name) {
		// TODO Auto-generated method stub
		List<Object> paramList =new ArrayList<Object>();
		String jpql="from FwDicEntity where name = ?1";
		paramList.add(name);
		List<FwDicEntity>  list= fwDicRepository.queryByJpql(jpql, paramList);
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}


}
