package com.example.xddemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class XdDemoApplication {


    public static void main(String[] args) {

        List<String> list = Lists.newArrayList("测试1", "测试2", "测试3");

        System.out.println(JSON.toJSONString(list));


        String a = "[\"测试1\",\"测试2\",\"测试3\"]";


        String b = "1、患者在佩戴动态血糖监测的5天期间，血糖表现为餐后血糖升高，最高达12mmol/L，一般餐后高峰在8.5-11.3mmol/L，餐后高峰一般持续1-1.5小时，3小时后基本可降至空腹水平。建议可适当减少进餐量，或适当增加餐后运动量，餐后1小时去运动。2基本没有低血糖发生。 3建议目前降糖方案基础上，如进餐碳水化合物较多可加用糖苷酶抑制剂，如米格列醇。4全天血糖达标时间在理想范围内。";

        List<String> strings1 = Lists.newArrayList();
        try {

            strings1 = JSONArray.parseArray(b, String.class);
        } catch (Exception e) {
            strings1.add(b);
        }


        System.out.println(strings1.toString());

        List<String> strings = JSONArray.parseArray(a, String.class);

        System.out.println(strings.toString());
    }

}
