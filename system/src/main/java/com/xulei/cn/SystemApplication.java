package com.xulei.cn;

import com.xulei.cn.utils.IdWorker;
import com.xulei.cn.utils.SmsSendUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.xulei.cn")
public class SystemApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SystemApplication.class).properties("spring.config.location=classpath:/application-dev.yml").run(args);
//        SpringApplication.run(SystemApplication.class, args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }

    @Bean
    public SmsSendUtils smsSendUtils(){
        return new SmsSendUtils();
    }

}
