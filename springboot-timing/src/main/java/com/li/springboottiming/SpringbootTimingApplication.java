package com.li.springboottiming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootTimingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTimingApplication.class, args);
    }

}
