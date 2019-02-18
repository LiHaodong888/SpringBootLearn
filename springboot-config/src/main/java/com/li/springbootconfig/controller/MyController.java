package com.li.springbootconfig.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MyController
 * @Author lihaodong
 * @Date 2019/2/18 23:33
 * @Mail lihaodongmail@163.com
 * @Description
 * @Version 1.0
 **/

@Configuration
@PropertySource(value = "classpath:my.properties")
@ConfigurationProperties(prefix = "my")
@RestController
public class MyController {

    @Value("${my.name}")
    private String name;

    @Value("${my.age}")
    private int age;

    @RequestMapping(value = "/user")
    public String miya() {
        return name + "," + age;
    }

}
