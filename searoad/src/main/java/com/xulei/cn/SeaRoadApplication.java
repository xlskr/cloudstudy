package com.xulei.cn;

import com.xulei.cn.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients(basePackages = {"com.xulei.cn.fegin"})
@SpringBootApplication
public class SeaRoadApplication {

    public static void main(String[] args) {
         SpringApplication.run(SeaRoadApplication.class, args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }

}
