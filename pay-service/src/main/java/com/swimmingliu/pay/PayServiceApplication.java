package com.swimmingliu.pay;

import com.swimmingliu.api.config.DefaultFeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.swimmingliu.api.client", defaultConfiguration = DefaultFeignConfig.class)
@MapperScan("com.swimmingliu.pay.mapper")
@SpringBootApplication
public class PayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayServiceApplication.class, args);
    }

}
