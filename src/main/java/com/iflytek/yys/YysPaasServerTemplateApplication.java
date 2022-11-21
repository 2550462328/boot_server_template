package com.iflytek.yys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 核心启动器
 *
 * Author XiongZhi.Wu 2019/4/24
 */

//@EnableScheduling
//@EnableAsync
//@EnableCaching
@SpringBootApplication
@ServletComponentScan(basePackages = "com.iflytek.yys.base.filter")
public class YysPaasServerTemplateApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(YysPaasServerTemplateApplication.class, args);
    }
}
