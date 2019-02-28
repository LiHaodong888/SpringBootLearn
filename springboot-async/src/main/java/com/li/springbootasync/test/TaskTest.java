package com.li.springbootasync.test;

import com.li.springbootasync.task.Task;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName TaskTest
 * @Author lihaodong
 * @Date 2019/2/26 17:49
 * @Mail lihaodongmail@163.com
 * @Description
 * @Version 1.0
 **/
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TaskTest {

    @Autowired
    private Task task;

    @Test
    public void test01() throws Exception {
        task.doTaskOne();
        task.doTaskTwo();
        task.doTaskThree();
    }

    @Test
    public void test02() throws Exception {
        long start = System.currentTimeMillis();
        Future<String> task1 = task.doTaskOne();
        Future<String> task2 = task.doTaskTwo();
        Future<String> task3 = task.doTaskThree();
        while(true) {
            if(task1.isDone() && task2.isDone() && task3.isDone()) {
                // 三个任务都调用完成，退出循环等待
                break;
            }
            Thread.sleep(1000);
        }
        long end = System.currentTimeMillis();
        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");

    }

    @Test
    public void test03() throws Exception {
        task.doTaskOne();
        task.doTaskTwo();
        task.doTaskThree();
        Thread.currentThread().join();
    }

    @Test
    @SneakyThrows
    public void test() {
        for (int i = 0; i < 10000; i++) {
            task.doTaskOne();
            task.doTaskTwo();
            task.doTaskThree();
            if (i == 9999) {
                System.exit(0);
            }
        }
    }

    @Test
    public void test04() throws Exception {
        Future<String> futureResult = task.run();
        String result = futureResult.get(5, TimeUnit.SECONDS);
        log.info(result);
    }
}
