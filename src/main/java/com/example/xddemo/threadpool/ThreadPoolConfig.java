package com.example.xddemo.threadpool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.Executor;

/**
 * Author: xuedong
 * Date: 2024/8/28
 */
@Configuration
public class ThreadPoolConfig {


    @Resource
    private AlertingRejectedExecutionHandler alertingRejectedExecutionHandler;


    @Bean("taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //设置线程池参数信息
        taskExecutor.setCorePoolSize(2);
        taskExecutor.setMaxPoolSize(2);
        taskExecutor.setQueueCapacity(2);
        taskExecutor.setKeepAliveSeconds(60);
        taskExecutor.setThreadNamePrefix("cic-executor-");
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setAwaitTerminationSeconds(60);
        //修改拒绝策略为使用当前线程执行
        taskExecutor.setRejectedExecutionHandler(alertingRejectedExecutionHandler);
        //初始化线程池
        taskExecutor.initialize();
        return taskExecutor;
    }
}
