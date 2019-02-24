package com.li.springbootrabbitmq.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author lihaodong
 **/

@Service
public class MqReceiver {

    private Logger logger = LoggerFactory.getLogger(MqReceiver.class);

    /**
     * Direct 交换机模式
     */
    @RabbitListener(queues = MqConfig.QUEUE)
    public void receive(String message){
        logger.info("receive message" + message);
    }

    /**
     * Topic 交换机模式
     */
    @RabbitListener(queues = MqConfig.TOPIC_QUEUE1)
    public void receiveTopic1(String message){
        logger.info("receive topic queue1 message: " + message);
    }

    @RabbitListener(queues = MqConfig.TOPIC_QUEUE2)
    public void receiveTopic2(String message){
        logger.info("receive topic queue2 message: " + message);
    }

    /**
     * Fanout模式 交换机Exchange
     */
    @RabbitListener(queues = MqConfig.FANOUT_QUEUE1)
    public void receiveFanout1(String message){
        logger.info("receive fanout queue1 message: " + message);
    }

    @RabbitListener(queues = MqConfig.FANOUT_QUEUE2)
    public void receiveFanout2(String message){
        logger.info("receive fanout queue2 message: " + message);
    }

    /**
     * Header模式 交换机Exchange
     */
    @RabbitListener(queues = MqConfig.HEADERS_QUEUE)
    public void receiveFanout2(byte[] message){
        logger.info("receive headers queue message: " + new String(message));
    }

}
