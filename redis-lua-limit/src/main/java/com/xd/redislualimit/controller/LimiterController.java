package com.xd.redislualimit.controller;

import com.xd.redislualimit.annotation.RateLimit;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Classname LimiterController
 * @Description 测试控制层
 * @Author 李号东 lihaodongmail@163.com
 * @Date 2019-05-25 20:16
 * @Version 1.0
 */
@RestController
public class LimiterController {

    @Autowired
    private RedisTemplate redisTemplate;

    // 10 秒中，可以访问5次
    @RateLimit(key = "test", time = 10, count = 5)
    @GetMapping("/test")
    public String luaLimiter() {
        // 简单测试方法
        RedisAtomicInteger entityIdCounter = new RedisAtomicInteger("counter", redisTemplate.getConnectionFactory());
        String date = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS");
        return date + " 累计访问次数：" + entityIdCounter.getAndIncrement();
    }
}
