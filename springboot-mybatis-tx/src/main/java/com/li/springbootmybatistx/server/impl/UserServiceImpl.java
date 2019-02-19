package com.li.springbootmybatistx.server.impl;

import com.li.springbootmybatistx.dao.UserMapper;
import com.li.springbootmybatistx.server.UserServer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

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


    @Transactional
    @Override
    public void transfer() {
        //用户1减10块 用户2加10块
        userMapper.updateUser(BigDecimal.valueOf(90), 1);
        int i = 1 / 0;
        userMapper.updateUser(BigDecimal.valueOf(110), 2);
    }
}
