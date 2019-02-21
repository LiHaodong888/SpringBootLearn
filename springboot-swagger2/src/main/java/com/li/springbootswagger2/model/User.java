package com.li.springbootswagger2.model;

/**
 * @ClassName User
 * @Author lihaodong
 * @Date 2019/2/21 15:33
 * @Mail lihaodongmail@163.com
 * @Description
 * @Version 1.0
 **/

public class User {

    private int id;
    private int age;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
