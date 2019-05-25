package com.xd.redislualimit.annotation;


import java.lang.annotation.*;

/**
 * @Author 李号东
 * @Description 限流注解
 * @Date 17:49 2019-05-25
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimit {

    /**
     * 限流唯一标示
     *
     * @return
     */
    String key() default "";

    /**
     * 限流时间
     *
     * @return
     */
    int time();

    /**
     * 限流次数
     *
     * @return
     */
    int count();
}
