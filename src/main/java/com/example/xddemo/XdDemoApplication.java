package com.example.xddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class XdDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(XdDemoApplication.class, args);
    }

}
