package com.framework.admin.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.framework.admin.thymeleaf.dialect.ThymeleafDialect;
import com.framework.common.base.repository.impl.BaseRepositoryImpl;

@Configuration
//用于扫描@Repository
@EnableJpaRepositories(basePackages = "com.framework",repositoryBaseClass = BaseRepositoryImpl.class)
//用于扫描JPA实体类 @Entity
@EntityScan("com.framework")
public class BaseConfig {
    @Bean
    public ThymeleafDialect thymeleafDialect() {
        return new ThymeleafDialect();
    }
}
