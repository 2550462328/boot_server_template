package com.iflytek.yys.base.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BootApplicationRunner implements ApplicationRunner {
    
    @Override
    public void run(ApplicationArguments args) {
        log.info("服务器启动完成（ApplicationRunner）");
    }
}