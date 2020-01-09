package com.hanyl.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.common.redis.RedisService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MQSender {

	@Autowired
	AmqpTemplate amqpTemplate;
	
	public void send(Object msg) {
		String str = RedisService.beanTOString(msg);
		log.info("send messge");
		amqpTemplate.convertAndSend(MQconfig.QUEUE, str);
	}
}
