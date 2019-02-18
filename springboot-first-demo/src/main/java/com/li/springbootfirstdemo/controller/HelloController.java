package com.li.springbootfirstdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloController
 * @Author lihaodong
 * @Date 2019/2/18 17:28
 * @Mail lihaodongmail@163.com
 * @Description
 * @Version 1.0
 **/

@RestController
public class HelloController {

    @RequestMapping("/index")
    public String index() {
        return "Hello from Spring Boot!";
    }

}
