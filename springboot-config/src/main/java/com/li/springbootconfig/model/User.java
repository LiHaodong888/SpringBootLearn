package com.li.springbootconfig.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @ClassName User
 * @Author lihaodong
 * @Date 2019/2/18 23:39
 * @Mail lihaodongmail@163.com
 * @Description
 * @Version 1.0
 **/

@Configuration
@PropertySource(value = "classpath:my.properties")
@ConfigurationProperties(prefix = "my")
public class User {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
