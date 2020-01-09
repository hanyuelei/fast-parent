package com.framework.admin.runner;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.framework.admin.entity.FwConfigEntity;
import com.framework.admin.service.FwConfigService;
import com.framework.admin.util.SysConfigRedis;
/**
 * 
* @ClassName: SysConfigStartService  
* @Description: TODO(系统配置启动项目自定执行)  
* @author hanyl
* @date 2019年6月25日 下午2:12:50  
*
 */
@Component
@Order(value = 1)
public class SysConfigStartService implements ApplicationRunner {
	private static Logger logger = LoggerFactory.getLogger(SysConfigStartService.class);
	@Autowired
	private FwConfigService fwConfigService;
	
	@Autowired
	private SysConfigRedis sysConfigRedis;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		logger.info("=========== 项目启动后，初始化 系统配置 =============");
		List<FwConfigEntity> list=fwConfigService.getAllList();
		if(null!=list&&list.size()>0) {
			for(FwConfigEntity entity:list) {
				sysConfigRedis.saveOrUpdate(entity);
			}
		}
	}

}
