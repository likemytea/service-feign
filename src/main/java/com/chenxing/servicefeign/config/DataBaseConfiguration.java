package com.chenxing.servicefeign.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * Description:
 * 
 * @author liuxing
 * @date 2018年5月9日
 * @version 1.0
 */
@Configuration
public class DataBaseConfiguration {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Bean(destroyMethod = "close", initMethod = "init")
	@ConfigurationProperties("spring.datasource")
	public com.alibaba.druid.pool.DruidDataSource dataSource() {

		log.info("注入druid！！！");
		DruidDataSource druidDataSource = new DruidDataSource();
		return druidDataSource;
	}
}