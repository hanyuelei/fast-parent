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
import com.framework.admin.entity.FwConfigEntity;
import com.framework.admin.service.FwConfigService;
import com.framework.admin.util.SysConfigRedis;
import com.framework.common.util.result.Result;
/**
 * 
* @ClassName: FwConfigController  
* @Description: TODO(配置类)  
* @author hanyl
* @date 2019年6月18日 下午5:32:11  
*
 */
@Controller
@RequestMapping("admin/system/config")
public class FwConfigController {

	@Autowired
	private FwConfigService fwConfigService;
	@Autowired
	private SysConfigRedis sysConfigRedis;
	@PostMapping(value = "list")
	@ResponseBody
	public Result list(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "limit", defaultValue = "10") Integer limit, HttpServletRequest request) {
   
		return fwConfigService.getConfigPage(page, limit, request);
	}
	@SysLog("添加配置")
	@PostMapping(value = "add")
	@ResponseBody
	public Result save(FwConfigEntity  config) {
        boolean boo=fwConfigService.checkKey(config.getParamKey(), null);
        if(!boo) {
        	return Result.failure("参数KEY不可能重复");
        }
        fwConfigService.save(config);
        sysConfigRedis.saveOrUpdate(config);
    	return Result.success();

	}
	@SysLog("修改配置")
	@PostMapping(value = "edit")
	@ResponseBody
	public Result edit(FwConfigEntity config,@RequestParam Long cid) {
        boolean boo=fwConfigService.checkKey(config.getParamKey(), cid);
        if(!boo) {
        	return Result.failure("参数KEY不可重复");
        }
        FwConfigEntity updateEntity=fwConfigService.get(cid);
        updateEntity.setParamKey(config.getParamKey());
        updateEntity.setParamValue(config.getParamValue());
        updateEntity.setRemark(config.getRemark());
        fwConfigService.update(updateEntity);
        sysConfigRedis.saveOrUpdate(updateEntity);
    	return Result.success();

	}
	@SysLog("删除配置")
	@GetMapping(value = "del")
	@ResponseBody
	public Result del( @RequestParam(value = "id") Long id) {
		FwConfigEntity  fwConfigEntity =fwConfigService.findById(id);
		if(null!=fwConfigEntity) {
			fwConfigService.delete(fwConfigEntity);
			 sysConfigRedis.saveOrUpdate(fwConfigEntity);
		}
		return Result.success();
	}
	
//======================跳转路由========================================
	
	@GetMapping(value = "list")
	public String list() {
		return "admin/config/configList";
	}
	@GetMapping(value = "add")
	public String add() {
		return "admin/config/configAdd";
	}
	@GetMapping(value = "edit")
	public String edit(ModelMap modelMap,@RequestParam Long id) {
		modelMap.put("config", fwConfigService.get(id));
		return "admin/config/configEdit";
	}
//	public static void main(String[] args) {
//		System.out.println(JSON.toJSON(R.success("操作成功").setAny("1", "2")));
//	}
}
