package com.li.springbootconfig.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ConfigController
 * @Author lihaodong
 * @Date 2019/2/18 23:10
 * @Mail lihaodongmail@163.com
 * @Description
 * @Version 1.0
 **/
@RestController
public class ConfigController {

    @Value("${my.publicNumber}")
    private String publicNumber;

    @Value("${my.blog}")
    private String webBlog;

    @RequestMapping(value = "/my")
    public String miya() {
        return "公众号:" + publicNumber + ",网页博客:" + webBlog;
    }



}
