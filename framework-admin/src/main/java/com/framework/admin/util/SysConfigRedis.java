

package com.framework.admin.util;


import org.springframework.stereotype.Component;

import com.framework.admin.entity.FwConfigEntity;
import com.framework.common.redis.RedisService;
import com.framework.common.redis.key.SysConfigKey;

import lombok.extern.slf4j.Slf4j;

/**
 * 
* @ClassName: SysConfigRedis  
* @Description: TODO(系统配置redis)  
* @author hanyl
* @date 2019年6月25日 下午1:04:07  
*
 */
@Component
@Slf4j
public class SysConfigRedis {


	
    public void saveOrUpdate(FwConfigEntity config) {
        if(config == null){
            return ;
        }
        log.info("添加or修改系统配置：key:"+config.getParamKey()+" value:"+config.getParamValue());
        RedisService.set(SysConfigKey.getConfig,config.getParamKey(), config.getParamValue());
    }

    public void delete(FwConfigEntity config) {
        log.info("删除系统配置：key:"+config.getParamKey()+" value:"+config.getParamValue());
        RedisService.delete(SysConfigKey.getConfig,config.getParamKey());
    }
	public static String getConfigByKey(String key) {
		
		 return RedisService.get(SysConfigKey.getConfig,key,String.class);
		
	}
}
