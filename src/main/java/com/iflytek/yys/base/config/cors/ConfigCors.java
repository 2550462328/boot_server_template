package com.iflytek.yys.base.config.cors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;

/**
 * 配置Cors
 *
 * Author XiongZhi.Wu 2020/12/14
 */
@Slf4j
@Configuration
public class ConfigCors {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    @Bean
    public FilterRegistrationBean<Filter> corsFilterRegistrationBean() {
        FilterRegistrationBean<Filter> corsFilterRegistrationBean = new FilterRegistrationBean<>();
        corsFilterRegistrationBean.setFilter(new DelegatingFilterProxy("corsFilter"));
        corsFilterRegistrationBean.addUrlPatterns("/*");
        corsFilterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        log.info("==========注册 'CorsFilter' 成功==========");
        return corsFilterRegistrationBean;
    }
}