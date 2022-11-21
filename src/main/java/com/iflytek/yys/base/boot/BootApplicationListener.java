package com.iflytek.yys.base.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BootApplicationListener implements ApplicationListener<ApplicationEvent> {
    
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        log.info("Spring 监听上下文事件（BootApplicationListener）:{}",event);
    }
}