package com.framework.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.admin.service.FwScheduleJobLogService;
import com.framework.common.util.result.Result;

/**
 * 
 *
 * @author hanyl
 * @date 2019-06-26 21:15:48
 */
@Controller
@RequestMapping("admin/system/fwScheduleJobLog")
public class FwScheduleJobLogController {

	@Autowired
	private FwScheduleJobLogService fwScheduleJobLogService;
	
	
	@PostMapping(value = "list")
	@ResponseBody
	public Result list(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "limit", defaultValue = "10") Integer limit, HttpServletRequest request) {
		Result r = Result.success();

    	List<Object> objList = new ArrayList<Object>();
        int paramPosition = 1;
		String beanName=request.getParameter("beanName");
	
        String jpql="from FwScheduleJobLogEntity where 1=1 ";
        if(StringUtils.isNotEmpty(beanName)) {
        	jpql+=" and beanName like ?"+paramPosition+"";
        	paramPosition+=1;
        	objList.add("%"+beanName+"%");
        }
    	String methodName=request.getParameter("methodName");
        if(StringUtils.isNotEmpty(methodName)) {
        	jpql+=" and method like ?"+paramPosition+"";
        	paramPosition+=1;
        	objList.add("%"+methodName+"%");
        }
        jpql+="order by id desc";
        Pageable pageable = PageRequest.of(page, limit, null);
    	PageImpl pageData = (PageImpl)fwScheduleJobLogService.getPageByJpql(jpql, objList, pageable);
    	r.setAny("count",pageData.getTotalElements());
        r.setAny("data",pageData.getContent());
        return r;
	}
	@GetMapping(value = "list")
	public String list() {
		return "admin/fwScheduleJobLog/fwScheduleJobLogList";
	}

}
 