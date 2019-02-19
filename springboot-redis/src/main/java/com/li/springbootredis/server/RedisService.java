package com.li.springbootredis.server;

public interface RedisService {

    /**
     * redis字符串操作 设置key value
     * @param key
     * @param value
     */
    void setValue(String key,Object value);

    /**
     * redis字符串操作 通过key取值
     * @param key
     * @return
     */
    Object getValue(String key);
}
