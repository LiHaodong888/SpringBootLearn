package com.xd.mybatisplusmultitenancy.test;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xd.mybatisplusmultitenancy.MybatisPlusMultiTenancyApplication;
import com.xd.mybatisplusmultitenancy.config.MyContext;
import com.xd.mybatisplusmultitenancy.entity.User;
import com.xd.mybatisplusmultitenancy.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Wrapper;

/**
 * @Classname MybatisPlusMultiTenancyApplicationTests
 * @Description TODO
 * @Author Created by Lihaodong (alias:小东啊) lihaodongmail@163.com
 * @Date 2019-08-09 22:50
 * @Version 1.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.JVM)
@SpringBootTest(classes = MybatisPlusMultiTenancyApplication.class)
public class MybatisPlusMultiTenancyApplicationTests {


    @Autowired
    private MyContext apiContext;

    @Autowired
    private UserMapper userMapper;

    /**
     * 模拟当前系统的多租户Id
     */
    @Before
    public void before() {
        // 在上下文中设置当前多租户id
        apiContext.setCurrentTenantId(1L);
    }

    @Test
    public void insert() {
        // 新增数据
        User user = new User().setName("小明");
        //判断一个条件是true还是false
        Assert.assertTrue(userMapper.insert(user) > 0);
        user = userMapper.selectById(user.getId());
        log.info("插入数据:{}", user);
        // 判断是否相等
        Assert.assertEquals(apiContext.getCurrentTenantId(), user.getTenantId());
    }

    @Test
    public void selectList() {
        userMapper.selectList(null).forEach((e) -> {
            log.info("查询数据{}", e);
            Assert.assertEquals(apiContext.getCurrentTenantId(), e.getTenantId());
        });
    }
}
