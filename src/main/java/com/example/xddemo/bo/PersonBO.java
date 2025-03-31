package com.example.xddemo.bo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author: xuedong
 * Date: 2024/2/27
 *
 * @author xuedong
 */
@Slf4j
@Data
public class PersonBO {


    public static void main(String[] args) {

        // 创建一个线程安全的 List
        List<String> debugMsg = new ArrayList<>();

        // 创建一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        // 并发添加元素
        for (int i = 0; i < 100; i++) {
            final int taskId = i;
            executorService.execute(() -> {
                String message = "Task-" + taskId + ": Debug Message";
                debugMsg.add(message);
                System.out.println("Added: " + message);
            });
        }





        // 打印最终的 List 内容
        System.out.println("\nFinal List Contents:");
        debugMsg.forEach(System.out::println);


    }
}



