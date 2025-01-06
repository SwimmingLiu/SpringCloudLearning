package com.swimmingliu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.swimmingliu.mapper")
@SpringBootApplication
public class SpringCloudLearningApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudLearningApplication.class, args);
    }
}