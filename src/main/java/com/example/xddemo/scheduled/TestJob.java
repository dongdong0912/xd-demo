package com.example.xddemo.scheduled;

import com.example.xddemo.feign.FeignTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Author: xuedong
 * Date: 2024/1/3
 */
@Component
@Slf4j
public class TestJob {


    @Resource
    private FeignTest feignTest;


    /**
     * 每十分钟执行一次
     */
    @DistributedScheduled(cron = "*/1  * * * * ?")
    public void extraAreaJob() {
        log.info("每秒执行一次");
        String s = feignTest.simpleWeather("温州", "8beaa2d050e6143a6160d550e178f835");
        log.info(s);
    }
}
