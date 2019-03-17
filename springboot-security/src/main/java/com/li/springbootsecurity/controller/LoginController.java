package com.li.springbootsecurity.controller;

import com.li.springbootsecurity.bo.ResponseUserToken;
import com.li.springbootsecurity.bo.ResultCode;
import com.li.springbootsecurity.bo.ResultJson;
import com.li.springbootsecurity.model.User;
import com.li.springbootsecurity.security.SecurityUser;
import com.li.springbootsecurity.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname LoginController
 * @Description 测试
 * @Author 李号东 lihaodongmail@163.com
 * @Date 2019-03-16 10:06
 * @Version 1.0
 */
@Controller
public class LoginController {

    @Autowired
    private IUserService userService;

    @Value("${jwt.header}")
    private String tokenHeader;

    /**
     * @Author 李号东
     * @Description 登录
     * @Date 10:18 2019-03-17
     * @Param [user]
     * @return com.li.springbootsecurity.bo.ResultJson<com.li.springbootsecurity.bo.ResponseUserToken>
     **/
    @RequestMapping(value = "/login")
    @ResponseBody
    public ResultJson<ResponseUserToken> login(User user) {
        System.out.println(user);
        ResponseUserToken response = userService.login(user.getUsername(), user.getPassword());
        return ResultJson.ok(response);
    }

    /**
     * @Author 李号东
     * @Description 获取用户信息 在WebSecurityConfig配置只有admin权限才可以访问 主要用来测试权限
     * @Date 10:17 2019-03-17
     * @Param [request]
     * @return com.li.springbootsecurity.bo.ResultJson
     **/
    @GetMapping(value = "/user")
    @ResponseBody
    public ResultJson getUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        SecurityUser securityUser = userService.getUserByToken(token);
        return ResultJson.ok(securityUser);
    }

    public static void main(String[] args) {
        String password = "admin";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
        String enPassword = encoder.encode(password);
        System.out.println(enPassword);
    }
}
