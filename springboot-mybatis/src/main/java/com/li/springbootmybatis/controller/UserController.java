package com.li.springbootmybatis.controller;

import com.li.springbootmybatis.model.User;
import com.li.springbootmybatis.server.UserServer;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<User> getAccounts() {
        return userServer.findUserList();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getAccountById(@PathVariable("id") int id) {
        return userServer.findUser(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateAccount(@PathVariable("id") int id, @RequestParam(value = "name", required = true) String name) {
        int t = userServer.update(name, id);
        if (t == 1) {
            return "success";
        } else {
            return "fail";
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "id") int id) {
        int t = userServer.delete(id);
        if (t == 1) {
            return "success";
        } else {
            return "fail";
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String postAccount(String name) {
        int t = userServer.add(name);
        if (t == 1) {
            return "success";
        } else {
            return "fail";
        }
    }

}
