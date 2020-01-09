package com.framework.admin.service;

import javax.servlet.http.HttpServletRequest;

import com.framework.admin.entity.FwDicEntity;
import com.framework.common.base.service.BaseService;
import com.framework.common.util.result.Result;

public interface FwDicService extends BaseService<FwDicEntity, Long> {
	/**
	 * 
	* @Title: getDicPage  
	* @Description: TODO(分页列表)  
	* @param @param page  页面
	* @param @param limit 每页条数
	* @param @param request
	* @param @return    设定文件  
	* @return R    返回类型  
	* @throws
	 */
	Result getDicPage(Integer page,Integer limit,HttpServletRequest request);
	/**
	 * 
	* @Title: checkName  
	* @Description: TODO(检查name是否可用，)  
	* @param @param name
	* @param @param id
	* @param @return    设定文件  
	* @return boolean   true可用 返回类型  
	* @throws
	 */
	boolean checkName(String name,Long id);
	/**
	 * 
	* @Title: getModelByName  
	* @Description: TODO(根据字典标识查询)  
	* @param @param name
	* @param @return    设定文件  
	* @return FwDicEntity    返回类型  
	* @throws
	 */
	FwDicEntity getModelByName(String name);
}


