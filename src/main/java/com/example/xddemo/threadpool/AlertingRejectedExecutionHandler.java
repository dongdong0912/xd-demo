package com.example.xddemo.threadpool;

import org.springframework.stereotype.Component;

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

        long taskCount = executor.getTaskCount();
        System.out.println("任务数量"+taskCount);
        //抛出异常，包含报警逻辑
        String string = executor.toString();
        System.out.println(string);

    }

}
