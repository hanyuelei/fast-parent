package com.framework.admin.service;

import java.util.List;
import java.util.Map;

import com.framework.admin.entity.FwPermissionEntity;
import com.framework.admin.entity.vo.PermissionTreeVO;
import com.framework.common.base.service.BaseService;
import com.framework.common.util.result.Result;

public interface FwPermissionService extends BaseService<FwPermissionEntity, Long> {
	/**
	 * 
	* @Title: getListByParentId  
	* @Description: TODO(根据父id查询)  
	* @param @return    设定文件  
	* @return List<FwPermissionEntity>    返回类型  
	* @throws
	 */
	List<FwPermissionEntity> queryListByParentId(Long id);
	/**
	 * 
	* @Title: queryTreePermission  
	* @Description: TODO(获取父子节点)  
	* @param @return    设定文件  
	* @return List<FwPermissionEntity>    返回类型  
	* @throws
	 */
	List<FwPermissionEntity> queryTreePermission( Map<String, String> authMap);
	/**
	 * 
	* @Title: queryParentPermission  
	* @Description: TODO(获取所有一级菜单)  
	* @param @return    设定文件  
	* @return List<FwPermissionEntity>    返回类型  
	* @throws
	 */
	List<FwPermissionEntity> queryParentPermission();
	/**
	 * 
	* @Title: queryPermissionTree  
	* @Description: TODO(获取授权需要展示的树形权限)  
	* @param @return    设定文件  
	* @return List<PermissionTreeVO>    返回类型  
	* @throws
	 */
	List<PermissionTreeVO> queryPermissionTree(Long roleId);
	
	Map<Long,Object> querypsermissionByRoleId(Long roleId);
	
	List queryPerListByRoleId(Long roleId);
	
	List<FwPermissionEntity> queryByParentIdAndUrl(Long parentId,String url);
	
	List<FwPermissionEntity> queryByType(Integer type);
	/**
	* @Title: queryByType  
	* @Description: TODO(* 检查url是否重复)  
	* @param @param type
	* @param @return    设定文件  
	* @return boolean  true 不重复返回类型  
	* @throws
	 */
	boolean checkUrl(Integer type,String url,Long id);
	
	Result saveOrUpdate(FwPermissionEntity permission,Long pid);
	/**
	 *   按排序查询所有权限
	 */
	List<FwPermissionEntity> findAllListBySort();
}
