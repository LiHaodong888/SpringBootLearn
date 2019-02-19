package com.li.springbootmybatis.server.impl;

import com.li.springbootmybatis.dao.UserMapper;
import com.li.springbootmybatis.model.User;
import com.li.springbootmybatis.server.UserServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Author lihaodong
 * @Date 2019/2/19 09:22
 * @Mail lihaodongmail@163.com
 * @Description
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserServer {

    @Resource
    private UserMapper userMapper;

    @Override
    public int add(String name) {
        return userMapper.add(name);
    }

    @Override
    public int update(String name, int id) {
        return update(name, id);
    }

    @Override
    public int delete(int id) {
        return userMapper.delete(id);
    }

    @Override
    public User findUser(int id) {
        return userMapper.findUser(id);
    }

    @Override
    public List<User> findUserList() {
        return userMapper.findUserList();
    }
}
