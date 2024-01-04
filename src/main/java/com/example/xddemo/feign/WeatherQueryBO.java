package com.example.xddemo.feign;

import lombok.Data;

/**
 * Author: xuedong
 * Date: 2024/1/4
 */
@Data
public class WeatherQueryBO {

    /**
     * 城市
     */
    private String city;
    /**
     * key
     */
    private String key;
}
