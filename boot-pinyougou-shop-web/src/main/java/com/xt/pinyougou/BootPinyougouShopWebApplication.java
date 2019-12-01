package com.xt.pinyougou;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

@Import(FdfsClientConfig.class)
// 解决 jmx 重复注册 bean 的问题
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
@SpringBootApplication
public class BootPinyougouShopWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootPinyougouShopWebApplication.class, args);
    }

}
