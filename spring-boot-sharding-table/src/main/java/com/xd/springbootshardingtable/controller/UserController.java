package com.xd.springbootshardingtable.controller;

import com.xd.springbootshardingtable.entity.User;
import com.xd.springbootshardingtable.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Classname UserController
 * @Description 用户测试控制类
 * @Author 李号东 lihaodongmail@163.com
 * @Date 2019-05-26 17:36
 * @Version 1.0
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/select")
    public List<User> select() {
        return userService.getUserList();
    }

    @GetMapping("/insert")
    public Boolean insert(User user) {
        return userService.save(user);
    }

}
