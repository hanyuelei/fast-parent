package com.framework.admin.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.framework.admin.entity.FwScheduleJobEntity;
import com.framework.common.base.service.BaseService;
import com.framework.common.util.result.Result;

public interface FwScheduleJobService extends BaseService<FwScheduleJobEntity, Long> {
	/**
	 * 
	* @Title: getOpendList  
	* @Description: TODO(获取开启的list)  
	* @param @return    设定文件  
	* @return List<FwScheduleJobEntity>    返回类型  
	* @throws
	 */
	List<FwScheduleJobEntity> getOpendList();
	/**
	 * 
	* @Title: saveJob  
	* @Description: TODO(保存)  
	* @param @param scheduleJob    设定文件  
	* @return void    返回类型  
	* @throws
	 */
	void saveJob(FwScheduleJobEntity scheduleJob);
	/**
	 * 更新定时任务
	 */
	void updateJob(FwScheduleJobEntity scheduleJob);
	
	/**
	 * 批量删除定时任务
	 */
	void deleteBatch(Long[] jobIds);
	
	/**
	 * 批量更新定时任务状态
	 */
	int updateBatch(Long[] jobIds, int status);
	
	/**
	 * 立即执行
	 */
	void run(Long[] jobIds);
	
	/**
	 * 暂停运行
	 */
	void pause(Long[] jobIds);
	
	/**
	 * 恢复运行
	 */
	void resume(Long[] jobIds);
	
	 Result getJobList(Integer page,Integer limit,HttpServletRequest request);
	 /**
	  * 
	 * @Title: checkJob  
	 * @Description: TODO(校验job是否重复)  
	 * @param @param beanName
	 * @param @param methodName
	 * @param @param id
	 * @param @return    设定文件  
	 * @return boolean    返回类型  
	 * @throws
	  */
	 boolean checkJob(String beanName,String methodName,Long id);
}


