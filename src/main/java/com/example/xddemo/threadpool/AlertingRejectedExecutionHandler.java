package com.example.xddemo.threadpool;

import org.springframework.stereotype.Component;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Author: xuedong
 * Date: 2024/8/28
 */
@Component
public class AlertingRejectedExecutionHandler implements RejectedExecutionHandler {


    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        //抛出异常，包含报警逻辑
        throw new RejectedExecutionException("任务 " + r.toString() + " 被拒绝", new Throwable("报警: 线程池已满"));
    }

}
