package com.li.springbootmybatistx.model;

import java.math.BigDecimal;

/**
 * @ClassName User
 * @Author lihaodong
 * @Date 2019/2/19 09:20
 * @Mail lihaodongmail@163.com
 * @Description
 * @Version 1.0
 **/

public class User {

    private int id;
    private String name;
    private BigDecimal money;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
