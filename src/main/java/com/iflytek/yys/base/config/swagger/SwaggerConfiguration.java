package com.iflytek.yys.base.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * swagger 配置类
 *
 * @author huizhang43
 */
@Profile({"dev", "uat"})
@EnableOpenApi
@Configuration
public class SwaggerConfiguration implements WebMvcConfigurer {
    
    @Bean
    public Docket createRestApi() {
        // 创建 Docket 对象
        return new Docket(DocumentationType.SWAGGER_2)
                // 文档类型，使用 Swagger2
                .apiInfo(this.apiInfo())
                // 设置 API 信息
                // 扫描 Controller 包路径，获得 API 接口
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.iflytek.yys"))
                .paths(PathSelectors.any())
                // 构建出 Docket 对象
                .build();
    }
    
    /**
     * 创建 API 信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("云医生接口文档")
                .description("云医生微服务接口")
                .version("1.0.0_snapshot")
                // 版本号
                .contact(new Contact("YYS_GROUP", "https://www.iflytek.com", "huizhang43@iflytek.com"))
                // 联系人
                .build();
    }
}