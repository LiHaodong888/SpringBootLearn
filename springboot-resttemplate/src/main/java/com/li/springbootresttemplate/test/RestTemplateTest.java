package com.li.springbootresttemplate.test;

import com.li.springbootresttemplate.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName RestTemplateTest
 * @Author lihaodong
 * @Date 2019/2/22 18:17
 * @Mail lihaodongmail@163.com
 * @Description
 * @Version 1.0
 **/

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestTemplateTest {

    private Logger logger = LoggerFactory.getLogger(RestTemplateTest.class);

    @Autowired
    RestTemplate restTemplate;

    @Test
    public void get() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:8080/users", String.class);
        logger.info("返回信息:"+responseEntity.getBody());
        logger.info("状态信息:"+responseEntity.getStatusCode());
        logger.info("状态码:"+responseEntity.getStatusCodeValue());
    }
    /**
     * 通过id获取用户信息
     */
    @Test
    public void getObjectParam() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:8080/users/{1}", String.class,2);
        logger.info("返回信息:"+responseEntity.getBody());
        logger.info("状态信息:"+responseEntity.getStatusCode());
        logger.info("状态码:"+responseEntity.getStatusCodeValue());
    }


    /**
     * 通过id获取用户信息
     */
    @Test
    public void getObject() {
        User user = restTemplate.getForObject("http://localhost:8080/users/{1}", User.class,2);
        logger.info(user.toString());
    }

    /**
     * 添加用户信息
     */
    @Test
    public void post() {
        User user = new User();
        user.setAge(18);
        user.setName("小四");
        user.setId(2);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8080/users", user, String.class);
        logger.info("返回信息:"+responseEntity.getBody());
        logger.info("状态信息:"+responseEntity.getStatusCode());
        logger.info("状态码:"+responseEntity.getStatusCodeValue());
    }

    /**
     * 根据Id删除
     */
    @Test
    public void delete() {

        restTemplate.delete("http://localhost:8080/users/1",String.class);
    }

    /**
     * 根据Id修改
     */
    @Test
    public void put() {
        User user = new User();
        user.setAge(18);
        user.setName("小五");
        user.setId(2);
        restTemplate.put("http://localhost:8080/users/2",user);
    }


}
