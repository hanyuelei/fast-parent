

package com.framework.common.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
* @ClassName: RedisKeys  
* @Description: TODO(redis keys)  
* @author hanyl
* @date 2019年6月25日 下午2:08:40  
*
 */
@Component
public class RedisKeys {
	/**
	 * 静态的变量需要写非静态set方法赋值，否则获取不到yml的值
	 */
    private static  String configKey;
    
    @Value("${custom.configKey}")
    public void setConfigKey(String configKey) {
    	RedisKeys.configKey = configKey;
	}

	public static String getSysConfigKey(){
        return configKey;
    }
}
