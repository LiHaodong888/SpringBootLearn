package com.li.springbootrabbitmq.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lihaodong
 **/
@Service
public class MqSender {

    private Logger logger = LoggerFactory.getLogger(MqSender.class);

    @Autowired
    private AmqpTemplate amqpTemplate;


    /**
     * Direct 交换机模式
     */
    public void send(Object message){
        logger.info("send topic message: " + message);
        amqpTemplate.convertAndSend(MqConfig.QUEUE, message);
    }

    /**
     * Topic 交换机模式
     */
    public void sendTopic(Object message){
        logger.info("send topic message: " + message);
        amqpTemplate.convertAndSend(MqConfig.TOPIC_EXCHANGE,"topic.key1",message+"1");
        amqpTemplate.convertAndSend(MqConfig.TOPIC_EXCHANGE,"topic.key2",message+"2");
    }

    /**
     * Fanout模式 交换机Exchange
     */
    public void sendFanout(Object message){
        logger.info("send fanout message: " + message);
        amqpTemplate.convertAndSend(MqConfig.FANOUT_EXCHANGE,"",message+"1");
    }

    /**
     * Header模式 交换机Exchange
     *"header1","value1"要与队列初始化的时候一样
     */
    public void sendHeaders(Object message){
        logger.info("send headers message: " + message);
        MessageProperties properties = new MessageProperties();
        properties.setHeader("header1","value1");
        properties.setHeader("header2","value2");
        Message obj = new Message(message.toString().getBytes(),properties);
        amqpTemplate.convertAndSend(MqConfig.HEADERS_EXCHANGE,"",obj);
    }
}
