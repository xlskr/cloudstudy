package com.xulei.cn;

import com.xulei.cn.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.xulei.cn.fegin"})
public class ShopsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopsApplication.class, args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }
}
