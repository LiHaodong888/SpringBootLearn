package com.li.springbootmongdb.test;

import com.li.springbootmongdb.SpringbootMongdbApplication;
import com.li.springbootmongdb.dao.MongoTestDao;
import com.li.springbootmongdb.model.MongoTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootMongdbApplication.class)
public class MongdbTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private MongoTestDao mtdao;

    @Test
    public void saveTest() {
        MongoTest mgtest = new MongoTest();
        mgtest.setId(String.valueOf(12));
        mgtest.setAge(33);
        mgtest.setName("ceshi");
        mgtest.setCreate_time(System.currentTimeMillis());
        mtdao.saveTest(mgtest);
    }

    @Test
    public void findTestByName() {
        MongoTest mgtest = mtdao.findTestByName("ceshi");
        System.out.println("mgtest is " + mgtest);
    }

    @Test
    public void updateTest() {
        MongoTest mgtest = new MongoTest();
        mgtest.setId(String.valueOf(12));
        mgtest.setAge(44);
        mgtest.setName("ceshi2");
        mtdao.updateTest(mgtest);
    }

    @Test
    public void deleteTestById() {
        mtdao.deleteTestById(String.valueOf(12));
    }
    @Test
    public void distinct() {
        mtdao.distinct();
    }
}