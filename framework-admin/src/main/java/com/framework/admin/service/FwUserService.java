package com.framework.admin.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.framework.admin.entity.FwUserEntity;
import com.framework.common.base.service.BaseService;
import com.framework.common.util.result.Result;
@Service
public interface FwUserService extends BaseService<FwUserEntity, Long> {

	  FwUserEntity getUserByUsername(String userName);
	  /**
	   * 
	  * @Title: checkUserName  
	  * @Description: TODO(校验用户名是否重复)  
	  * @param @param userName
	  * @param @param id
	  * @param @return    设定文件  
	  * @return boolean    返回类型  
	  * @throws
	   */
	  boolean checkUserName(String userName,Long id);
	  /**
	   * 
	  * @Title: getParentIds  
	  * @Description: TODO(获取所有父id)  
	  * @param @param userId
	  * @param @return    设定文件  
	  * @return String    返回类型  
	  * @throws
	   */
	  String getParentIds(Long userId);
	  /**
	   * 
	  * @Title: getUserList  
	  * @Description: TODO(用户列表)  
	  * @param @param page
	  * @param @param limit
	  * @param @param request
	  * @param @return    设定文件  
	  * @return ResponseEntity    返回类型  
	  * @throws
	   */
	  Result getUserList(Integer page,Integer limit,HttpServletRequest request);
	  /**
	   * 
	  * @Title: saveOrUpdateUser  
	  * @Description: TODO(保存和修改)  
	  * @param @param user
	  * @param @param userId
	  * @param @param request
	  * @param @return    设定文件  
	  * @return ResponseEntity    返回类型  
	  * @throws
	   */
	  Result saveOrUpdateUser(FwUserEntity user,Long userId);
	  /**
	   * 
	  * @Title: getRoleByUserId  
	  * @Description: TODO(根据id获取角色)  
	  * @param @param userId
	  * @param @return    设定文件  
	  * @return ResponseEntity    返回类型  
	  * @throws
	   */
	  Result getRoleByUserId(Long userId);
	  /**
	   * 
	  * @Title: authrole  
	  * @Description: TODO(保存用户授权)  
	  * @param @param userid
	  * @param @param roles
	  * @param @return    设定文件  
	  * @return ResponseEntity    返回类型  
	  * @throws
	   */
	  Result authrole(Long userid,String roles);
	  
}
