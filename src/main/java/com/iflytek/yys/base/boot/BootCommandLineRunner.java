package com.iflytek.yys.base.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BootCommandLineRunner implements CommandLineRunner {
    
    @Override
    public void run(String... args) throws Exception {
        log.info("服务器启动完成（CommandLineRunner）");
    }
}