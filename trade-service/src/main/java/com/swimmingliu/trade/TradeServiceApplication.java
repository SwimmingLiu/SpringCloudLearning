package com.swimmingliu.trade;

import com.swimmingliu.api.config.DefaultFeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.swimmingliu.api.client", defaultConfiguration = DefaultFeignConfig.class)
@MapperScan("com.swimmingliu.trade.mapper")
@SpringBootApplication
public class TradeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradeServiceApplication.class, args);
	}

}
