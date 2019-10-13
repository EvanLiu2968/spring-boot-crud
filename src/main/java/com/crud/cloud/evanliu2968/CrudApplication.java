package com.crud.cloud.evanliu2968;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * crud服务平台启动入口
 * 开启定时+异步
 */
@SpringBootApplication(scanBasePackages = "com.crud.cloud.evanliu2968.*")
@MapperScan("com.crud.cloud.evanliu2968.dao")
@EnableScheduling
@EnableAsync
public class CrudApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrudApplication.class, args);
    }
}
