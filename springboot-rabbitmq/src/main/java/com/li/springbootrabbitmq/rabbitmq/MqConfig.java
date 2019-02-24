package com.li.springbootrabbitmq.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lihaodong
 **/
@Configuration
public class MqConfig {

    // 测试队列
    static final String QUEUE = "queue";
    // Topic 交换机模式队列
    static final String TOPIC_QUEUE1 = "topic.queue1";
    static final String TOPIC_QUEUE2 = "topic.queue2";
    static final String TOPIC_EXCHANGE = "topicExchange";
    // Fanout模式队列
    static final String FANOUT_QUEUE1 = "fanout.queue1";
    static final String FANOUT_QUEUE2 = "fanout.queue2";
    static final String FANOUT_EXCHANGE = "fanoutExchange";
    // Header模式队列
    static final String HEADERS_QUEUE = "headers.queue";
    static final String HEADERS_EXCHANGE = "headersExchange";


    /**
     * Direct 交换机模式
     */
    //队列
    @Bean
    public Queue queue() {
        return new Queue(QUEUE,true);
    }

    /**
     * Topic 交换机模式
     */
    @Bean
    public Queue topicQueue1(){
        return new Queue(TOPIC_QUEUE1,true);
    }
    @Bean
    public Queue topicQueue2(){
        return new Queue(TOPIC_QUEUE2,true);
    }
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }
    /**
     * 绑定Exchange和queue 正确地将消息路由到指定的Queue
     */
    @Bean
    public Binding topicBinding1(){
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("topic.key1");
    }
    // #通配符，代表多个单词
    @Bean
    public Binding topicBinding2(){
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("topic.#");
    }

    /**
     * Fanout模式 交换机Exchange
     */
    @Bean
    public Queue fanoutQueue1(){
        return new Queue(FANOUT_QUEUE1,true);
    }
    @Bean
    public Queue fanoutQueue2(){
        return new Queue(FANOUT_QUEUE2,true);
    }

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public Binding fanoutBinding1(){
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }

    @Bean
    public Binding fanoutBinding2(){
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }

    /**
     * Header模式 交换机Exchange
     */
    @Bean
    public HeadersExchange headersExchange(){
        return new HeadersExchange(HEADERS_EXCHANGE);
    }
    @Bean
    public Queue headersQueue(){
        return new Queue(HEADERS_QUEUE,true);
    }
    @Bean
    public Binding headersBinding(){
        Map<String, Object> map = new HashMap<>();
        map.put("header1","value1");
        map.put("header2","value2");
        return BindingBuilder.bind(headersQueue()).to(headersExchange()).whereAll(map).match();
    }
}
