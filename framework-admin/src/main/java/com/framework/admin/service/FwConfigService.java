package com.framework.admin.service;

import javax.servlet.http.HttpServletRequest;

import com.framework.admin.entity.FwConfigEntity;
import com.framework.common.base.service.BaseService;
import com.framework.common.util.result.Result;

public interface FwConfigService extends BaseService<FwConfigEntity, Long> {
	/**
	 * 
	* @Title: checkKey  
	* @Description: TODO(检查key是否可用)  
	* @param @param key
	* @param @param id
	* @param @return    设定文件  
	* @return boolean    返回类型   true 不重复
	* @throws
	 */
	boolean checkKey(String key,Long id);
	
	Result getConfigPage(Integer page,Integer limit,HttpServletRequest request);
}
