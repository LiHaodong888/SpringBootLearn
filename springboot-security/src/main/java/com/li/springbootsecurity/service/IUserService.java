package com.li.springbootsecurity.service;

import com.li.springbootsecurity.bo.ResponseUserToken;
import com.li.springbootsecurity.model.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.li.springbootsecurity.security.SecurityUser;

/**
 * <p>
 *  用户服务类
 * </p>
 *
 * @author lihaodong
 * @since 2019-03-14
 */
public interface IUserService extends IService<User> {


    /**
     * 通过用户名查找用户
     *
     * @param username 用户名
     * @return 用户信息
     */
    User findByUserName(String username);

    /**
     * 登陆
     * @param username
     * @param password
     * @return
     */
    ResponseUserToken login(String username, String password);


    /**
     * 根据Token获取用户信息
     * @param token
     * @return
     */
    SecurityUser getUserByToken(String token);
}
