package com.iflytek.yys.base.config.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.incrementer.PostgreKeyGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * Mybatis配置
 *
 * @author huizhang43
 */
@Slf4j
@Configuration
@MapperScan("com.iflytek.yys.business.mapper")
public class MybatisPlusConfig {
    
    @Resource(name = "mybatisPlusAutoFillProperties")
    private MybatisPlusAutoFillProperties mybatisPlusAutoFillProperties;
    
    /***
     * 分页插件配置
     * @return
     */
    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }
    
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "database.field.auto-fill", name = "enabled", havingValue = "true", matchIfMissing = true)
    public MetaObjectHandler metaObjectHandler() {
        log.info("mybatisPlusAutoFillProperties:{}", mybatisPlusAutoFillProperties);
        return new DateMetaObjectHandler(mybatisPlusAutoFillProperties);
    }
    
    @Bean(name = "pgKeyGenerator")
    public PostgreKeyGenerator postgreKeyGenerator() {
        return new PostgreKeyGenerator();
    }
}
