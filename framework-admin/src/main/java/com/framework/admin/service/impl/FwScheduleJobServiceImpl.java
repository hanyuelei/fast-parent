package com.framework.admin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.framework.admin.entity. FwScheduleJobEntity;
import com.framework.admin.job.util.ScheduleUtils;
import com.framework.admin.repository.FwScheduleJobRepository;
import com.framework.admin.service. FwScheduleJobService;
import com.framework.common.base.service.impl.BaseServiceImpl;
import com.framework.common.util.Constants;
import com.framework.common.util.result.Result;

@Service
public class FwScheduleJobServiceImpl extends BaseServiceImpl<FwScheduleJobEntity, Long> implements FwScheduleJobService {

	private FwScheduleJobRepository fwScheduleJobRepository;
	public FwScheduleJobServiceImpl(FwScheduleJobRepository fwScheduleJobRepository) {
		super(fwScheduleJobRepository);
		// TODO Auto-generated constructor stub
		this.fwScheduleJobRepository=fwScheduleJobRepository;
	}
	@Autowired
    private Scheduler scheduler;
	
	/**
	 * 项目启动时，初始化定时器
	 */
	@PostConstruct
	public void init(){
		List<FwScheduleJobEntity> scheduleJobList =this.getOpendList();
		for(FwScheduleJobEntity scheduleJob : scheduleJobList){
			CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getId());
            //如果不存在，则创建
            if(cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            }else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveJob(FwScheduleJobEntity scheduleJob) {
//		scheduleJob.setStatus(Constants.ScheduleStatus.NORMAL.getValue());
		fwScheduleJobRepository.save(scheduleJob);
        
        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
    }
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateJob(FwScheduleJobEntity scheduleJob) {
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
                
        this.update(scheduleJob);
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] jobIds) {
    	for(Long jobId : jobIds){
    		ScheduleUtils.deleteScheduleJob(scheduler, jobId);
    	}
    	
    	//删除数据
    	this.delete(jobIds);
	}

	@Override
    public int updateBatch(Long[] jobIds, int status){
    	Map<String, Object> map = new HashMap<>(2);
    	int i=0;
    	for(Long jobId : jobIds){
    		FwScheduleJobEntity  fwScheduleJobEntity =this.findById(jobId);
    		if(null!=fwScheduleJobEntity) {
    			i+=1;
    			fwScheduleJobEntity.setStatus(status);
    			this.update(fwScheduleJobEntity);
    		}
    	}
		return i;
    }
    
	@Override
	@Transactional(rollbackFor = Exception.class)
    public void run(Long[] jobIds) {
    	for(Long jobId : jobIds){
    		ScheduleUtils.run(scheduler, this.findById(jobId));
    	}
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
    public void pause(Long[] jobIds) {
        for(Long jobId : jobIds){
    		ScheduleUtils.pauseJob(scheduler, jobId);
    	}
        
    	updateBatch(jobIds, Constants.ScheduleStatus.PAUSE.getValue());
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
    public void resume(Long[] jobIds) {
    	for(Long jobId : jobIds){
    		ScheduleUtils.resumeJob(scheduler, jobId);
    	}

    	updateBatch(jobIds, Constants.ScheduleStatus.NORMAL.getValue());
    }

	@Override
	public List<FwScheduleJobEntity> getOpendList() {
		List<Object> paramList =new ArrayList<Object>();
		String jpql="from FwScheduleJobEntity where status = ?1";
		paramList.add(0);
		return  fwScheduleJobRepository.queryByJpql(jpql, paramList);
	
	}

	@Override
	public Result getJobList(Integer page, Integer limit, HttpServletRequest request) {
		Result r = Result.success();
    	List<Object> objList = new ArrayList<Object>();
        int paramPosition = 1;
        String jpql="from FwScheduleJobEntity where 1=1 ";
        String beanName=request.getParameter("beanName");
        if(StringUtils.isNotEmpty(beanName)) {
        	jpql+=" and beanName like ?"+paramPosition+"";
        	paramPosition+=1;
        	objList.add("%"+beanName+"%");
        }
    	String methodName=request.getParameter("methodName");
        if(StringUtils.isNotEmpty(methodName)) {
        	jpql+=" and method like ?"+paramPosition+"";
        	paramPosition+=1;
        	objList.add("%"+methodName+"%");
        }
        jpql+="order by id desc";
        Pageable pageable = PageRequest.of(page, limit, null);
    	PageImpl pageData = (PageImpl)fwScheduleJobRepository.getPageByJpql(jpql, objList, pageable);
    	r.setAny("count",pageData.getTotalElements());
        r.setAny("data",pageData.getContent());
        return r;
	}

	@Override
	public boolean checkJob(String beanName, String methodName, Long id) {
		// TODO Auto-generated method stub
		List<Object> paramList =new ArrayList<Object>();
		String jpql="from FwScheduleJobEntity where beanName = ?1 and method=?2";
		paramList.add(beanName.trim());
		paramList.add(methodName.trim());
		if(null!=id) {
			jpql+=" and id <> ?3";
			paramList.add(id);
		}
		List<FwScheduleJobEntity> list=  fwScheduleJobRepository.queryByJpql(jpql, paramList);
		if(list.size()==0) {
			return true;
		}
		return false ;
	}


}
