package com.xulei.cn;

import com.xulei.cn.utils.IdWorker;
import com.xulei.cn.utils.RedisUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients(basePackages = {"com.xulei.cn.fegin"})
@SpringBootApplication
public class H5Application {

    public static void main(String[] args) {
         SpringApplication.run(H5Application.class, args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }

    @Bean
    public RedisUtil redisUtil(){
        return new RedisUtil();
    }
}
