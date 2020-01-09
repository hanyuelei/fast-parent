
package com.framework.admin.job.task;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试定时任务(演示Demo，可删除)
 *
 * testTask为spring bean的名称
 *
 */
@Component("testTask")
@Slf4j
public class TestTask implements ITask {

	@Override
	public void run(String params){
		log.info("TestTask定时任务正在执行，参数为："+params);
	}
	@Override
	public void task(String params) {
		log.info("2222定时任务正在执行，参数为："+params);
	}
}
