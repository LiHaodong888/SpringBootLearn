package com.li.springbootconfig.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName ConfigBean
 * @Author lihaodong
 * @Date 2019/2/18 23:20
 * @Mail lihaodongmail@163.com
 * @Description
 * @Version 1.0
 **/
@ConfigurationProperties(prefix = "my")
@Component
public class ConfigBean {

    private String publicNumber;
    private String blog;
    private String name;
    private int age;

    public String getPublicNumber() {
        return publicNumber;
    }

    public void setPublicNumber(String publicNumber) {
        this.publicNumber = publicNumber;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

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
