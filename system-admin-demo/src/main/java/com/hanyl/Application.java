package com.hanyl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//去除springSecurity


//@ComponentScan(basePackages = {"com.framework","com.hanyl"})
@SpringBootApplication(scanBasePackages="com.framework,com.hanyl")
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer{

	public static Logger logger = LoggerFactory.getLogger(Application.class);
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		logger.info("《使用外部tomcat配置启动springboot》");
		return application.sources(Application.class);
	}
	public static void main(String[] args) {
		logger.info("《内置启动springboot》");
		SpringApplication.run(Application.class, args);
	}
	
	

}
