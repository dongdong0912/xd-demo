package com.example.xddemo.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Author: xuedong
 * Date: 2024/1/3
 */
@Component
@Slf4j
public class TestJob {


    /**
     * 每十分钟执行一次
     */
    @DistributedScheduled(cron = "*/1  * * * * ?")
    public void extraAreaJob() {
        log.info("每秒执行一次");
    }
}
