package com.li.springbootredis.model;

/**
 * @ClassName User
 * @Author lihaodong
 * @Date 2019/2/19 21:15
 * @Mail lihaodongmail@163.com
 * @Description
 * @Version 1.0
 **/

public class User {

    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
