package com.framework.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.framework.admin.entity.FwImageEntity;
import com.framework.admin.repository.FwImageRepository;
import com.framework.admin.service.FwImageService;
import com.framework.admin.util.SysConfigRedis;
import com.framework.common.base.service.impl.BaseServiceImpl;
import com.framework.common.util.result.Result;
@Service
public class FwImageServiceImpl extends BaseServiceImpl<FwImageEntity, Long> implements FwImageService {

	private FwImageRepository fwImageRepository;
	public FwImageServiceImpl(FwImageRepository fwImageRepository) {
		super(fwImageRepository);
		// TODO Auto-generated constructor stub
		this.fwImageRepository=fwImageRepository;
	}
	@Override
	public Result getImagePage(Integer page, Integer limit, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Result r = Result.success();
    	List<Object> objList = new ArrayList<Object>();
        int paramPosition = 1;
        String jpql="from FwImageEntity image where 1=1 ";
        String imageType=request.getParameter("imageType");
        if(StringUtils.isNotEmpty(imageType)) {
        	jpql+=" and image.type=?"+paramPosition+"";
        	objList.add(imageType);
        	paramPosition+=1;
        }
        String remark=request.getParameter("remark");
        if(StringUtils.isNotEmpty(remark)) {
        	jpql+=" and image.remark like ?"+paramPosition+"";
        	objList.add("%"+remark+"%");
        	paramPosition+=1;
        }
        jpql+="order by id desc";
        Pageable pageable = PageRequest.of(page, limit, null);
    	PageImpl pageData = (PageImpl)fwImageRepository.getPageByJpql(jpql, objList, pageable);
    	r.setAny("count",pageData.getTotalElements());
        r.setAny("data",pageData.getContent());
        r.setAny("pages",pageData.getTotalPages());
    	r.setAny("img_prefix", SysConfigRedis.getConfigByKey("img_prefix") );
        return r;
	}


}
