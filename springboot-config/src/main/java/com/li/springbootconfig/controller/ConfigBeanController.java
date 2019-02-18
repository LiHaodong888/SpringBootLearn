package com.li.springbootconfig.controller;

import com.li.springbootconfig.model.ConfigBean;
import com.li.springbootconfig.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ConfigBeanController
 * @Author lihaodong
 * @Date 2019/2/18 23:26
 * @Mail lihaodongmail@163.com
 * @Description
 * @Version 1.0
 **/

@RestController
@EnableConfigurationProperties({ConfigBean.class,User.class})
public class ConfigBeanController {

    @Autowired
    ConfigBean configBean;

    @RequestMapping(value = "/miya")
    public String my() {
        return configBean.getPublicNumber() + "," + configBean.getName() + ","
                + configBean.getBlog() + "," + configBean.getAge();
    }

    @Autowired
    User user;

    @RequestMapping(value = "/user1")
    public String user(){
        return user.getName()+user.getAge();
    }
}
