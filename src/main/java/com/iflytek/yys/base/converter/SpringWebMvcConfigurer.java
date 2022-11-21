package com.iflytek.yys.base.converter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Description:
 *
 * @author createdBy huizhang43.
 * @date createdAt 2022/5/9 14:17
 **/
@Configuration
public class SpringWebMvcConfigurer implements WebMvcConfigurer {
    
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.removeIf(converter -> converter instanceof FastJsonHttpMessageConverter);
        
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        //自定义配置...
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(
                SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.SkipTransientField, SerializerFeature.WriteNullStringAsEmpty);
        fastJsonHttpMessageConverter.setFastJsonConfig(config);
        converters.add(1, fastJsonHttpMessageConverter);
    }
}
