package com.xt.pinyougou;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan({"com.xt.pinyougou"})
@EnableDubbo
@SpringBootApplication
public class BootPinyougouSellergoodsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootPinyougouSellergoodsServiceApplication.class, args);
    }

}
