package com.framework.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class RedisFactory {
	@Autowired
	RedisConifg redisConifg;

	@Bean
	public JedisPool JedisPoolFactory() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(redisConifg.getPoolMaxTotal());
		poolConfig.setMaxIdle(redisConifg.getPoolMaxIdle());
		poolConfig.setMaxWaitMillis(redisConifg.getPoolMaxWait() * 1000);
		JedisPool jp = new JedisPool(poolConfig,redisConifg.getHost(),redisConifg.getPort(),
				redisConifg.getTimeout()*1000,redisConifg.getPassword(),0);
		return jp;
		
	}
}
