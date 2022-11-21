package com.iflytek.yys.base.config.http;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 配置RestTemplate
 * <p>
 * Author xzwu6 2021/1/15
 */
@Configuration
public class RestTemplateConfig {

    /**
     * 默认超时时间
     */
    private static final Integer DEFAULT_TIME_OUT_MILLS = 60 * 1000;

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setConnectTimeout(DEFAULT_TIME_OUT_MILLS);
        simpleClientHttpRequestFactory.setReadTimeout(DEFAULT_TIME_OUT_MILLS);
        return new RestTemplate(simpleClientHttpRequestFactory);
    }
}