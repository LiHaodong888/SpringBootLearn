package com.li.springbootredis.test;

import com.li.springbootredis.model.User;
import com.li.springbootredis.server.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName RedisTest
 * @Author lihaodong
 * @Date 2019/2/19 21:04
 * @Mail lihaodongmail@163.com
 * @Description
 * @Version 1.0
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    private static Logger logger = LoggerFactory.getLogger(RedisTest.class);


    @Autowired
    RedisService redisService;

    @Test
    public void testRedis() {
        redisService.setValue("name", "lihaodong");
        redisService.setValue("age", "18");
        logger.info(String.valueOf(redisService.getValue("name")));
        logger.info(String.valueOf(redisService.getValue("age")));
    }

    @Test
    public void testModelRedis() {
        User user = new User();
        user.setName("小王");
        user.setAge("22");
        redisService.setValue("first",user);
        User first = (User) redisService.getValue("first");
        logger.info("取出对象为{}",first);
    }



}
