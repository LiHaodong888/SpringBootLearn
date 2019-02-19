package com.li.springbootredis.server.Impl;

import com.li.springbootredis.server.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @ClassName RedisServiceImpl
 * @Author lihaodong
 * @Date 2019/2/19 21:00
 * @Mail lihaodongmail@163.com
 * @Description
 * @Version 1.0
 **/
@Service
public class RedisServiceImpl implements RedisService {


    /**
     * RedisTemplate 优雅操作redis的客户端
     * ValueOperations：简单K-V操作
     * SetOperations：set类型数据操作
     * ZSetOperations：zset类型数据操作
     * HashOperations：针对map类型的数据操作
     * ListOperations：针对list类型的数据操作
     */
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void setValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
