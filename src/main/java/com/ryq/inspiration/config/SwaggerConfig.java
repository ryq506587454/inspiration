package com.ryq.inspiration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//swagger2的配置文件
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    // Docket Api摘要
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //后面的包名表示要生成API文档的Controller包
                .apis(RequestHandlerSelectors.basePackage("com.ryq.inspiration.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("客户管理")
                .description("客户管理中心 API 1.0 操作文档")
                //服务条款网址
                .termsOfServiceUrl("http://www.ityouknow.com/")
                .version("1.0")
                .contact(new Contact("纯洁的微笑", "http://www.ityouknow.com/", "ityouknow@126.com"))
                .build();
    }
}
