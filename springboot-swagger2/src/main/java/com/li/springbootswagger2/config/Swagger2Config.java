package com.li.springbootswagger2.config;

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

/**
 * @ClassName Swagger2Config
 * @Author lihaodong
 * @Date 2019/2/21 15:25
 * @Mail lihaodongmail@163.com
 * @Description
 * @Version 1.0
 **/

@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 请求所在的包
                .apis(RequestHandlerSelectors.basePackage("com.li.springbootswagger2.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 文档标题
                .title("springboot利用swagger构建api文档")
                // 文档详细描述
                .description("简单优雅的restfun风格，http://www.lhdyx.cn")
                // 作者
                .contact(new Contact("小东", "http://www.lhdyx.cn", "lihaodongmail@163.com"))
                // 版本号
                .version("1.0")
                .build();
    }
}
