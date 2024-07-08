package com.example.xddemo.demo;

import java.util.Optional;

/**
 * Author: xuedong
 * Date: 2024/2/27
 */
public class TestDemo {


    public static void main(String[] args) {


        Optional<String> optionalWithValue = Optional.of("Hello, World!");
        Optional<String> emptyOptional = Optional.empty();

        // 使用 ifPresent 方法处理非空值
        optionalWithValue.ifPresent(value -> System.out.println("Value is present: " + value));
        emptyOptional.ifPresent(value -> System.out.println("This will not be printed."));

    }


}

