package com.xt.pinyougou;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableDubbo
@SpringBootApplication
public class BootPinyougouManagerWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootPinyougouManagerWebApplication.class, args);
    }

}
