package com.li.springbootmybatistx.controller;

import com.li.springbootmybatistx.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName UserController
 * @Author lihaodong
 * @Date 2019/2/19 09:25
 * @Mail lihaodongmail@163.com
 * @Description
 * @Version 1.0
 **/

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServer userServer;

    @RequestMapping(value = "/tran", method = RequestMethod.GET)
    public void getAccounts() {
        userServer.transfer();
    }


}
