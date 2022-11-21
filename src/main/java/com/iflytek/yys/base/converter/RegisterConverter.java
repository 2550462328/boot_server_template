package com.iflytek.yys.base.converter;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.iflytek.yys.base.converter.enums.DateFormatEnum;
import com.iflytek.yys.base.util.json.FastJsonConfigUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 注册转换器
 *
 * Author XiongZhi.Wu 2019/4/24
 */
@Slf4j
@Component
public class RegisterConverter {
    
    @Bean
    public ConversionServiceFactoryBean conversionServiceFactoryBean() {
        ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
        Set<Converter<?, ?>> converters = new HashSet<>();
        DateConverter dateConverter = new DateConverter();
        converters.add(dateConverter);
        log.info("==========添加 'DateConverter' 成功==========");
        conversionServiceFactoryBean.setConverters(converters);
        return conversionServiceFactoryBean;
    }
    
    @Bean
    public HttpMessageConverters httpMessageConverters() {
        Collection<HttpMessageConverter<?>> httpMessageConverterCollection = new ArrayList<>();
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = FastJsonConfigUtil.getFastJsonConfig();
        fastJsonConfig.setDateFormat(DateFormatEnum.DATE_FORMAT_DATETIMESTAMP.getValue());
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        httpMessageConverterCollection.add(fastJsonHttpMessageConverter);
        log.info("==========添加 'FastJsonHttpMessageConverter' 成功==========");
        return new HttpMessageConverters(true, httpMessageConverterCollection);
    }
}