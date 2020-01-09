package com.framework.admin.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.admin.annotation.SysLog;
import com.framework.admin.entity.FwPermissionEntity;
import com.framework.admin.entity.FwRoleEntity;
import com.framework.admin.entity.FwUserEntity;
import com.framework.admin.service.FwRoleService;
import com.framework.common.util.Constants;
import com.framework.common.util.result.Result;
/**
 * 
* @ClassName: UserRoleController  
* @Description: TODO(角色控制类)  
* @author hanyl
* @date 2019年5月27日 下午9:38:35  
*
 */
@Controller
@RequestMapping("admin/system/role")
public class FwRoleController {
	@Autowired
	private FwRoleService fwRoleService;
	
	@GetMapping(value = "list")
	public String list(HttpSession sessione) {
		return "admin/role/roleList";
	}

	@PostMapping(value = "list")
	@ResponseBody
	public Result list(HttpSession sessione, @RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "limit", defaultValue = "10") Integer limit, HttpServletRequest request) {

		return fwRoleService.getList(page, limit, request);

	}

	@GetMapping(value = "add")
	public String add(HttpSession sessione) {
		return "admin/role/roleAdd";
	}
	@SysLog("添加角色")
	@PostMapping(value = "add")
	@ResponseBody
	public Result save(FwRoleEntity role) {
		
		return fwRoleService.saveOrUpdate(role, null);

		
	}

	@GetMapping(value = "edit")
	public String add(ModelMap modelmap, Long id) {
		FwRoleEntity role = fwRoleService.get(id);
		modelmap.put("role", role);
		return "admin/role/roleEdit";
	}

	/**
	 * 
	* @Title: edit  
	* @Description: TODO(权限修改)  
	* @param @param id
	* @param @param role
	* @param @return    设定文件  
	* @return ResponseEntity    返回类型  
	* @throws
	 */
	@SysLog("修改角色")
	@PostMapping(value = "edit")
	@ResponseBody
	public Result edit(@RequestParam(value = "id") Long id,FwRoleEntity role) {
		
		return fwRoleService.saveOrUpdate(role, id);

	}

	@SysLog("删除角色")
	@GetMapping(value = "del")
	@ResponseBody
	public Result del(@RequestParam(value = "id") Long id) {
		Result r = Result.success();
		FwRoleEntity role = fwRoleService.get(id);
		if(role.getRoleCode().equals(Constants.DEFAULT_ADMIN_ROLECODE)) {
			return Result.failure("不能删除管理员角色");
		}
		Set<FwPermissionEntity> pers=role.getPermissions();
		if(null!=pers&&pers.size()>0) {
			return Result.failure("删除失败：请先解除角色与权限关系");
		}
		List<FwUserEntity> usesrs=role.getFwUsers();
		if(null!=usesrs&&usesrs.size()>0) {
			return Result.failure("删除失败：请先解除角色与用户关系");
		}
		fwRoleService.delete(role);
		return r;

	}

	@GetMapping(value = "authPermission")
	public String authPermission(ModelMap modelmap, Long id) {
		FwRoleEntity role = fwRoleService.get(id);
		modelmap.put("role", role);
		return "admin/role/authPermission";
	}
	/**
	 * 
	* @Title: userPermission  
	* @Description: TODO(获取所可操作权限和用户所有权限)  
	* @param @param id   角色id
	* @param @return    设定文件  
	* @return ResponseEntity    返回类型  
	* @throws
	 */
	@PostMapping(value = "rolePermission")
	@ResponseBody
	public Result userPermission(@RequestParam Long id) {

		return fwRoleService.rolePermission(id);

	}

	/**
	 * 
	* @Title: authrole  
	* @Description: TODO(保存用户授权)  
	* @param @param roleId  角色id
	* @param @param pers   权限id组
	* @param @return    设定文件  
	* @return ResponseEntity    返回类型  
	* @throws
	 */
	@SysLog("角色分配权限")
	@PostMapping(value = "authrole")
	@ResponseBody
	public Result authrole(@RequestParam Long roleId, String pers) {

		return fwRoleService.authrole(roleId, pers);

	}

}
