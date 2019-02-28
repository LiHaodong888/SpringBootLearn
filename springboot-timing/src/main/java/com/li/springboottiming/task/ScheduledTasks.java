package com.li.springboottiming.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName ScheduledTasks
 * @Author lihaodong
 * @Date 2019/2/28 12:03
 * @Mail lihaodongmail@163.com
 * @Description
 * @Version 1.0
 **/

@Component
public class ScheduledTasks {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");

    /**
     * fixedRate = 3000 每过3秒执行一次
     *
     */
//    @Scheduled(fixedRate = 3000)
    @Scheduled(cron = "*/3 * * * * ?")
    public void reportCurrentTime() {
        System.out.println("现在时间：" + DATE_FORMAT.format(new Date()));
    }

}
