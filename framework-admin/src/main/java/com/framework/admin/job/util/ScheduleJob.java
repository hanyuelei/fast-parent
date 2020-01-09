
package com.framework.admin.job.util;

import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.framework.admin.entity.FwScheduleJobEntity;
import com.framework.admin.entity.FwScheduleJobLogEntity;
import com.framework.admin.service.FwScheduleJobLogService;
import com.framework.common.util.SpringContextUtils;

import lombok.extern.slf4j.Slf4j;


/**
 * 
* @ClassName: ScheduleJob  
* @Description: TODO(定时任务)  
* @author hanyl
* @date 2019年6月26日 下午9:56:31  
* 热部署跟定时任务冲突，请先注释掉热部署依赖
 */
@Slf4j
public class ScheduleJob extends QuartzJobBean {
	private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        FwScheduleJobEntity scheduleJob = (FwScheduleJobEntity) context.getMergedJobDataMap()
        		.get(FwScheduleJobEntity.JOB_PARAM_KEY);
        
        //获取spring bean
        FwScheduleJobLogService scheduleJobLogService = (FwScheduleJobLogService) SpringContextUtils.getBean("fwScheduleJobLogService");
        
        //数据库保存执行记录
        FwScheduleJobLogEntity joblog = new FwScheduleJobLogEntity();
        joblog.setFwScheduleJob(scheduleJob);
        joblog.setBeanName(scheduleJob.getBeanName());
        joblog.setParams(scheduleJob.getParams());
        joblog.setMethod(scheduleJob.getMethod());
        
        //任务开始时间
        long startTime = System.currentTimeMillis();
        
        try {
            //执行任务
        	log.info("任务准备执行，任务ID：" + scheduleJob.getId());
			Object target = SpringContextUtils.getBean(scheduleJob.getBeanName());
			Method method = target.getClass().getDeclaredMethod(scheduleJob.getMethod(), String.class);
			method.invoke(target, scheduleJob.getParams());
			
			//任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			joblog.setTimes((int)times);
			//任务状态    0：成功    1：失败
			joblog.setStatus(0);
			
			log.info("任务执行完毕，任务ID：" + scheduleJob.getId() + "  总共耗时：" + times + "毫秒");
		} catch (Exception e) {
			logger.error("任务执行失败，任务ID：" + scheduleJob.getId(), e);
			
			//任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			joblog.setTimes((int)times);
			
			//任务状态    0：成功    1：失败
			joblog.setStatus(1);
			joblog.setError(StringUtils.substring(e.toString(), 0, 2000));
		}finally {
			scheduleJobLogService.save(joblog);
		}
    }
}
