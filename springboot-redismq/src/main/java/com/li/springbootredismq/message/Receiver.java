package com.li.springbootredismq.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName Receiver
 * @Author lihaodong
 * @Date 2019/2/23 18:19
 * @Mail lihaodongmail@163.com
 * @Description 接收者
 * @Version 1.0
 **/


/**
 * 这里声明了一个接受者，里面是提供了一个receiveMessage方法
 * 下面会使用MessageListenerAdapter对这个接收者进行代理，
 * 通过反射把消息传到receiveMessage中，也可以通过实现MessageListener接口来实现消息接受者，
 * 这里为了简单使用代理的方式
 */
public class Receiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);


    private CountDownLatch latch;

    @Autowired
    public Receiver(CountDownLatch latch) {
        this.latch = latch;
    }


    public void receiveMessage(String message) {
        LOGGER.info("Received:" + message + "");
        latch.countDown();
    }
}
