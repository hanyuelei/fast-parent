package com.framework.admin.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.framework.admin.entity.FwRoleEntity;
import com.framework.common.base.service.BaseService;
import com.framework.common.util.result.Result;

public interface FwRoleService extends BaseService<FwRoleEntity, Long> {
		/**
		 * 
		* @Title: checkRoleCode  
		* @Description: TODO(校验code是否重复)  
		* @param @param code
		* @param @param id
		* @param @return    设定文件  
		* @return boolean    返回类型  
		* @throws
		 */
	 boolean checkRoleCode(String code,Long id);
	 
	 List<FwRoleEntity> getRolesBycreateUserId(Long createUserId);
	 /**
	  * 
	 * @Title: getRolesByParentIds  
	 * @Description: TODO( 根据userId模糊匹配所有创建的权限)  
	 * @param @param createUserId
	 * @param @return    设定文件  
	 * @return List<FwRoleEntity>    返回类型  
	 * @throws
	  */
	 List<FwRoleEntity> getRolesLikeParentIds(Long userId);
	 /**
	  * 
	 * @Title: getList  
	 * @Description: TODO(角色列表)  
	 * @param @param page
	 * @param @param limit
	 * @param @param request
	 * @param @return    设定文件  
	 * @return ResponseEntity    返回类型  
	 * @throws
	  */
	 Result getList(Integer page, Integer limit, HttpServletRequest request);
	 /**
	  * 
	 * @Title: saveOrUpdate  
	 * @Description: TODO(保存和修改)  
	 * @param @param role
	 * @param @param roleId
	 * @param @return    设定文件  
	 * @return ResponseEntity    返回类型  
	 * @throws
	  */
	 Result saveOrUpdate(FwRoleEntity role,Long roleId);
	 /**
	  * 
	 * @Title: getRoleByCode  
	 * @Description: TODO(根据角色编码查询角色)  
	 * @param @param roleCode
	 * @param @return    设定文件  
	 * @return FwRoleEntity    返回类型  
	 * @throws
	  */
	 FwRoleEntity getRoleByCode(String roleCode);
	 /**
	  * 
	 * @Title: rolePermission  
	 * @Description: TODO(获取角色的权限 tree结构)  
	 * @param @param roleId
	 * @param @return    设定文件  
	 * @return ResponseEntity    返回类型  
	 * @throws
	  */
	 Result  rolePermission(Long roleId);
	 /**
	  * 
	 * @Title: authrole  
	 * @Description: TODO(保存角色授权)  
	 * @param @param roleId
	 * @param @param pers
	 * @param @return    设定文件  
	 * @return ResponseEntity    返回类型  
	 * @throws
	  */
	 Result authrole(Long roleId, String pers);
}
