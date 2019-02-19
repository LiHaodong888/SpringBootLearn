package com.li.springbootmybatistx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.li.springbootmybatistx.dao")
@SpringBootApplication
public class SpringbootMybatisTxApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisTxApplication.class, args);
    }

}
