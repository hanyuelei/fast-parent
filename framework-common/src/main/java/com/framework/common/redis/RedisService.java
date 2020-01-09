package com.framework.common.redis;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.framework.common.redis.key.AccessKey;
import com.framework.common.redis.key.BasePrefix;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
@Slf4j
public class RedisService {
	
	// 维护一个本类的静态变量
	private static RedisService redisService;

	@Autowired
	JedisPool JedisPool;
	
	@PostConstruct
	public void init() {
		redisService = this;
		redisService.JedisPool = this.JedisPool;
		log.info("############redis初始化成功###################");
	}

	/**
	 * 	取出缓存
	 */
	public static <T> T get(BasePrefix prefix,String key,Class<T> clazz) {
		Jedis jedis = null;
		try {
			jedis = redisService.JedisPool.getResource();
			String str = jedis.get(getRealKey(prefix, key));
			return stringToBean(str, clazz);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			redisService.returnToPool(jedis);
		}
		return null;
		
	}
	
	/**
	   * 设置缓存
	 * jedis.setex(key, seconds, value)做了两个操作，set和setexpire
	 */
	public static <T> T set(BasePrefix prefix, String key,T value) {
		Jedis jedis = null;
		try {
			String str = beanTOString(value);
			jedis = redisService.JedisPool.getResource();
			jedis.set(getRealKey(prefix, key), beanTOString(value));
			System.out.println("设置过期时间："+prefix.expireSeconds()+" str:"+str);
			if(prefix.expireSeconds() != 0) {
				jedis.expire(getRealKey(prefix, key), prefix.expireSeconds());//设置超时时间 单位秒
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			redisService.returnToPool(jedis);
		}
		return null;
		
	}

	/**
	 * 	判断key是否存在
	 */
	public static <T> boolean exists(BasePrefix prefix,String key) {
		Jedis jedis = null;
		try {
			jedis = redisService.JedisPool.getResource();
			return jedis.exists(getRealKey(prefix, key));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			redisService.returnToPool(jedis);
		}
		return false;
		
	}
	/**
	 * 	增加值
	 */
	public static <T> Long incr(BasePrefix prefix,String key) {
		Jedis jedis = null;
		try {
			jedis = redisService.JedisPool.getResource();
			return jedis.incr(getRealKey(prefix, key));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			redisService.returnToPool(jedis);
		}
		return null;
		
	}
	/**
	 * 	减少值
	 */
	public static <T> Long decr(BasePrefix prefix,String key) {
		Jedis jedis = null;
		try {
			jedis = redisService.JedisPool.getResource();
			return jedis.decr(getRealKey(prefix, key));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			redisService.returnToPool(jedis);
		}
		return null;
		
	}

	/**
	 * 删除key
	 */
	public static boolean delete(BasePrefix prefix,String key) {
		Jedis jedis = null;
		try {
			jedis = redisService.JedisPool.getResource();
			jedis.del(getRealKey(prefix, key));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			redisService.returnToPool(jedis);
		}
		return false;
	}
	
	private static String getRealKey(BasePrefix prefix,String key) {
		if(prefix == null) {
			return null;
		}
		return prefix.getPrefix() + key;
		
	}
	public static <T> String beanTOString(T value) {
		// TODO Auto-generated method stub
		if(value == null) {
			return null;
		}
		
		Class<?> clazz = value.getClass();
		if(clazz == int.class || clazz == Integer.class) {
			return ""+value;
		}else if(clazz == long.class || clazz == Long.class) {
			return ""+value;
		}else if(clazz == String.class) {
			return (String)value;
		}else {
			return JSONObject.toJSONString(value);
		}
		
	}

	@SuppressWarnings("unchecked")
	public static <T> T stringToBean(String str,Class<T> clazz) {
		// TODO Auto-generated method stub
		if(str == null || str.length()<0 || clazz ==null) {
			return null;
		}
		if(clazz == int.class || clazz == Integer.class) {
			return (T) Integer.valueOf(str);
		}else if(clazz == long.class || clazz == Long.class) {
			return (T)Long.valueOf(str);
		}else if(clazz == String.class) {
			return (T)str;
		}else {
//			return JSON.toJavaObject(JSON.parseObject(str), clazz);
			return JSONObject.parseObject(str, clazz);
		}
	}



	private void returnToPool(Jedis jedis) {
		// TODO Auto-generated method stub
		if(jedis != null) {
			jedis.close();
		}
		
	}

}
