package com.framework.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.framework.admin.annotation.SysLog;
import com.framework.admin.entity.FwScheduleJobEntity;
import com.framework.admin.service.FwScheduleJobService;
import com.framework.common.util.SpringContextUtils;
import com.framework.common.util.result.Result;
/**
 * 任务记录表
 *
 * @author hanyl
 * @date 2019-06-26 21:15:48
 */
@Controller
@RequestMapping("admin/system/fwScheduleJob")
public class FwScheduleJobController {

	@Autowired
	private FwScheduleJobService fwScheduleJobService;
	
	
	@PostMapping(value = "list")
	@ResponseBody
	public Result list(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "limit", defaultValue = "10") Integer limit, HttpServletRequest request) {

        return fwScheduleJobService.getJobList(page, limit, request);
	}
	@SysLog("添加定时任务")
	@PostMapping(value = "add")
	@ResponseBody
	public Result save(FwScheduleJobEntity  fwScheduleJob) {
        boolean boo=fwScheduleJobService.checkJob(fwScheduleJob.getBeanName(), fwScheduleJob.getMethod(), null);
        if(!boo) {
        	return Result.failure("不能添加重复任务");
        }
        try {
        	Object target = SpringContextUtils.getBean(fwScheduleJob.getBeanName());
		} catch (NoSuchBeanDefinitionException e) {
			// TODO: handle exception
			e.printStackTrace();
			return Result.failure("bean名称不存在");
		}
        fwScheduleJobService.saveJob(fwScheduleJob);
		return Result.success();

	}
	@SysLog("修改定时任务")
	@PostMapping(value = "edit")
	@ResponseBody
	public Result edit(FwScheduleJobEntity fwScheduleJob,@RequestParam Long uid) {
        boolean boo=fwScheduleJobService.checkJob(fwScheduleJob.getBeanName(), fwScheduleJob.getMethod(), uid);
        if(!boo) {
        	return Result.failure("不能添加重复任务");
        }
        try {
        	Object target = SpringContextUtils.getBean(fwScheduleJob.getBeanName());
		} catch (NoSuchBeanDefinitionException e) {
			// TODO: handle exception
			e.printStackTrace();
			return Result.failure("bean名称不存在");
		}
        FwScheduleJobEntity updateEntity=fwScheduleJobService.get(uid);
	    updateEntity.setBeanName(fwScheduleJob.getBeanName());	
	    updateEntity.setParams(fwScheduleJob.getParams());	
	    updateEntity.setCronExpression(fwScheduleJob.getCronExpression());	
	    updateEntity.setStatus(fwScheduleJob.getStatus());	
	    updateEntity.setRemark(fwScheduleJob.getRemark());	
	    updateEntity.setMethod(fwScheduleJob.getMethod());
        fwScheduleJobService.updateJob(updateEntity);
		return Result.success();

	}
	@SysLog("删除定时任务")
	@GetMapping(value = "del")
	@ResponseBody
	public Result del(@RequestParam String jobIds) {
		JSONArray jsonArray=JSONArray.parseArray(jobIds);
		Long[] ids = new Long[jsonArray.size()];
	  	for(int i=0;i<jsonArray.size();i++) {
    		Long rid=jsonArray.getLong(i);
    		ids[i]=rid;
    	}
		fwScheduleJobService.deleteBatch(ids);
		return Result.success();

	}
	
//=============================================================
	
	@GetMapping(value = "list")
	public String list() {
		return "admin/fwScheduleJob/fwScheduleJobList";
	}
	@GetMapping(value = "add")
	public String add() {
		return "admin/fwScheduleJob/fwScheduleJobAdd";
	}
	@GetMapping(value = "edit")
	public String edit(ModelMap modelMap,@RequestParam Long id) {
		modelMap.put("fwScheduleJob", fwScheduleJobService.get(id));
		return "admin/fwScheduleJob/fwScheduleJobEdit";
	}
	/**
	 * 立即执行任务
	 */
	@SysLog("立即执行任务")
	@GetMapping(value="run")
	@ResponseBody
	public Result run(@RequestParam String jobIds){
		JSONArray jsonArray=JSONArray.parseArray(jobIds);
		Long[] ids = new Long[jsonArray.size()];
	  	for(int i=0;i<jsonArray.size();i++) {
    		Long rid=jsonArray.getLong(i);
    		ids[i]=rid;
    	}
		fwScheduleJobService.run(ids);
		
		return Result.success();
	}
	
	/**
	 * 暂停定时任务
	 */
	@SysLog("暂停定时任务")
	@GetMapping(value="pause")
	@ResponseBody
	public Result pause(@RequestParam String jobIds){
		JSONArray jsonArray=JSONArray.parseArray(jobIds);
		Long[] ids = new Long[jsonArray.size()];
	  	for(int i=0;i<jsonArray.size();i++) {
    		Long rid=jsonArray.getLong(i);
    		ids[i]=rid;
    	}
		fwScheduleJobService.pause(ids);
		
		return Result.success();
	}
	
	/**
	 * 恢复定时任务
	 */
	@SysLog("恢复定时任务")
	@GetMapping(value="resume")
	@ResponseBody
	public Result resume(@RequestParam String jobIds){
		JSONArray jsonArray=JSONArray.parseArray(jobIds);
		Long[] ids = new Long[jsonArray.size()];
	  	for(int i=0;i<jsonArray.size();i++) {
    		Long rid=jsonArray.getLong(i);
    		ids[i]=rid;
    	}
		fwScheduleJobService.resume(ids);
		
		return Result.success();
	}
}
 