package com.framework.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.admin.annotation.SysLog;
import com.framework.admin.entity.FwPermissionEntity;
import com.framework.admin.entity.FwRoleEntity;
import com.framework.admin.entity.vo.MenuVO;
import com.framework.admin.service.FwPermissionService;
import com.framework.admin.service.FwRoleService;
import com.framework.admin.util.ConvertBeanUtil;
import com.framework.admin.util.SecurityUserUtil;
import com.framework.common.util.result.Result;

@Controller
@RequestMapping("admin/system/menu")
public class FwPermissionController {

	@Autowired
	private FwPermissionService fwPermissionService;
	@Autowired
	private FwRoleService fwRoleService;
	/**
	 * 目录级别
	 */
	private final static Integer PRETYPE=0;
	/**
	 * 
	 * @Title: treeList @Description: TODO(左侧菜单栏) @param @return 设定文件 @return
	 *         ResponseEntity 返回类型 @throws
	 */
	@RequestMapping("treeList")
	@ResponseBody
	public Result treeList() {
		Result r = Result.success();
        List<FwPermissionEntity> list = fwPermissionService.queryTreePermission(SecurityUserUtil.getAuthMap());
		List<MenuVO> menuList = ConvertBeanUtil.converMenuVo(list);
		Map<String, Object> menuMap = new HashMap<String, Object>();
		menuMap.put("systemMenu", menuList);
		return r.setAny("data", menuMap);
	}

	/**
	 * 
	 * @Title: treeList @Description: TODO(菜单列表) @param @return 设定文件 @return
	 *         ResponseEntity 返回类型 @throws
	 */
	@RequestMapping("treeMenu")
	@ResponseBody
	public Result treeMenu() {
		Result r = Result.success();
		List<FwPermissionEntity> list = fwPermissionService.findAllListBySort();
		return r.setAny("data", list);
	}

	@GetMapping(value = "list")
	public String list(HttpSession sessione) {
		return "admin/permission/permissionList";
	}

	@GetMapping(value = "add")
	public String add(HttpSession sessione, Long parentId, ModelMap modelMap) {
		if (null != parentId) {
			FwPermissionEntity parentMenu = fwPermissionService.get(parentId);
			modelMap.put("parentMenu", parentMenu);
			modelMap.put("menuType", parentMenu.getType()+1);
		}else {
			modelMap.put("menuType", 0);
		}
		return "admin/permission/permissionAdd";
	}

	@GetMapping(value = "edit")
	public String edit(@RequestParam(value="id") Long id, ModelMap modelMap, HttpSession sessione) {
		FwPermissionEntity pre = fwPermissionService.get(id);
		modelMap.put("pre", pre);
		modelMap.put("preList", fwPermissionService.queryByType(PRETYPE));
		
		return "admin/permission/permissionEdit";
	}

	@SysLog("添加权限")
	@PostMapping(value = "add")
	@ResponseBody
	public Result save(FwPermissionEntity permission) {
		
		return fwPermissionService.saveOrUpdate(permission, null);
	}
	@SysLog("修改权限")
	@PostMapping(value = "edit")
	@ResponseBody
	public Result edit( @ModelAttribute FwPermissionEntity permission,@RequestParam(value="pid") Long pid) {
	
		return fwPermissionService.saveOrUpdate(permission, pid);
	}
	@SysLog("删除权限")
	@GetMapping(value = "del")
	@ResponseBody
	public Result del(@RequestParam(value = "id")Long id) {
		Result r = Result.success();
    	FwPermissionEntity fwPermissionEntity=fwPermissionService.get(id);
    	List<FwPermissionEntity> list=fwPermissionService.queryListByParentId(id);
    	if(null!=list&&list.size()>0) {
    		 return Result.failure("删除失败：请先删除子权限");
    	}
    	List<FwRoleEntity> roles=fwPermissionEntity.getRoles();
    	if(null!=roles&&roles.size()>0) {
   		 return Result.failure("删除失败：请先解除与角色关系");
   	}
    	fwPermissionService.delete(fwPermissionEntity);;
		return r;
	
	}	
}
