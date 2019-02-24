package com.li.springbootrabbitmq.test;

import com.li.springbootrabbitmq.rabbitmq.MqSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName MqTest
 * @Author lihaodong
 * @Date 2019/2/24 20:54
 * @Mail lihaodongmail@163.com
 * @Description mq测试
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MqTest {

    @Autowired
    private MqSender mqSender;

    @Test
    public void mq() {
        mqSender.send("LiHaodong you're the hero,李浩东是成为海贼王的男人！");
    }

    @Test
    public void mq01() {
        mqSender.sendTopic("LiHaodong you're the hero,李浩东是成为海贼王的男人！");
    }
    @Test
    public void mq02() {
        mqSender.sendFanout("LiHaodong you're the hero,李浩东是成为海贼王的男人！");
    }

    @Test
    public void mq03() {
        mqSender.sendHeaders("LiHaodong you're the hero,李浩东是成为海贼王的男人！");
    }
}
