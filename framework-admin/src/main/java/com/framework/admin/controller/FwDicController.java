package com.framework.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.admin.annotation.SysLog;
import com.framework.admin.entity.FwDicEntity;
import com.framework.admin.service.FwDicService;
import com.framework.common.util.result.Result;
/**
 * 系统字典表
 *
 * @author hanyl
 * @date 2019-06-28 21:39:01
 */
@Controller
@RequestMapping("admin/system/fwDic")
public class FwDicController {

	@Autowired
	private FwDicService fwDicService;
	
	
	@PostMapping(value = "list")
	@ResponseBody
	public Result list(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "limit", defaultValue = "10") Integer limit, HttpServletRequest request) {
		
		return fwDicService.getDicPage(page, limit, request);
	}
	
	@SysLog("添加字典")
	@PostMapping(value = "add")
	@ResponseBody
	public Result save(FwDicEntity  fwDic) {
		if(!fwDicService.checkName(fwDic.getName(), null)) {
			Result.failure("字典标识不可重复");
		}
        fwDicService.save(fwDic);
		return Result.success();

	}
	@SysLog("修改字典")
	@PostMapping(value = "edit")
	@ResponseBody
	public Result edit(FwDicEntity fwDic,@RequestParam Long uid)
	{	
		if(!fwDicService.checkName(fwDic.getName(), uid)) {
			Result.failure("字典标识不可重复");
		}
        FwDicEntity updateEntity=fwDicService.get(uid);
	    updateEntity.setName(fwDic.getName());	
	    updateEntity.setTitle(fwDic.getTitle());	
	    updateEntity.setType(fwDic.getType());	
	    updateEntity.setValue(fwDic.getValue());	
	    updateEntity.setRemark(fwDic.getRemark());	
        fwDicService.update(updateEntity);
		return Result.success();

	}
	@SysLog("删除字典")
	@GetMapping(value = "del")
	@ResponseBody
	public Result del( @RequestParam(value = "id") Long id) {
		FwDicEntity  delEntity =fwDicService.findById(id);
		if(null!=delEntity) {
			fwDicService.delete(delEntity);
		}
		return Result.success();

	}
	
//=============================================================
	
	@GetMapping(value = "list")
	public String list() {
		return "admin/fwDic/fwDicList";
	}
	@GetMapping(value = "add")
	public String add() {
		return "admin/fwDic/fwDicAdd";
	}
	@GetMapping(value = "edit")
	public String edit(ModelMap modelMap,@RequestParam Long id) {
		modelMap.put("fwDic", fwDicService.get(id));
		return "admin/fwDic/fwDicEdit";
	}

}
 