package com.example.xddemo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Author: xuedong
 * Date: 2024/1/3
 */
@FeignClient(url = "http://apis.juhe.cn", name = "demo")
public interface FeignTest {

    @GetMapping(value = "/simpleWeather/query")
    String simpleWeather(@RequestParam("city") String city, @RequestParam("key") String key);


    @GetMapping(value = "/simpleWeather/query")
    String simpleWeather(WeatherQueryBO bo);
}
