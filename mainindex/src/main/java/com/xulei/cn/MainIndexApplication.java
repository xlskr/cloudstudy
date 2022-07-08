package com.xulei.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.xulei.cn.fegin.*"})
public class MainIndexApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainIndexApplication.class, args);
    }
}