package com.xd.springbootshardingtable.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xd.springbootshardingtable.entity.User;

import java.util.List;

/**
 * @Classname UserService
 * @Description 用户服务类
 * @Author 李号东 lihaodongmail@163.com
 * @Date 2019-05-26 17:31
 * @Version 1.0
 */
public interface UserService extends IService<User> {

    /**
     * 保存用户信息
     * @param entity
     * @return
     */
    @Override
    boolean save(User entity);

    /**
     * 查询全部用户信息
     * @return
     */
    List<User> getUserList();
}
