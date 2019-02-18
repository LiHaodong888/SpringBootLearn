package com.li.springbootfirstdemo.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName HelloControllerITest
 * @Author lihaodong
 * @Date 2019/2/18 17:42
 * @Mail lihaodongmail@163.com
 * @Description
 * @Version 1.0
 **/

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerITest {


    @Test
    public void testHello() {
        System.out.println("测试");
    }


}
