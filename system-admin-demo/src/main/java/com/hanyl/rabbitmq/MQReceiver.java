package com.hanyl.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MQReceiver {

	@RabbitListener(queues = MQconfig.QUEUE)
	public void receive(String message) {
		log.info("receive meaage:"+message);
	}
}
