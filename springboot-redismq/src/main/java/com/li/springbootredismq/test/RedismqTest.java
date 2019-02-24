package com.li.springbootredismq.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName RedismqTest
 * @Author lihaodong
 * @Date 2019/2/23 18:31
 * @Mail lihaodongmail@163.com
 * @Description
 * @Version 1.0
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedismqTest {


    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            stringRedisTemplate.convertAndSend("chat", "这是我发第" + i + "条的消息啊");
        }
    }


}
