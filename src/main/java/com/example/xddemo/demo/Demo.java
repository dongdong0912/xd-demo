package com.example.xddemo.demo;

import cn.hutool.json.JSONUtil;

/**
 * Author: xuedong
 * Date: 2024/7/16
 */
public class Demo {


    public static void main(String[] args) {

        Father father=new Father();


        System.out.println(Father.mobile);

        System.out.println(JSONUtil.toJsonStr(father));

        System.out.println(father.toString());

        System.out.println("测试");
    }

    public static int print1(Integer a) {

        a=a+19;
        return print2(a);
    }

    public static int print2(Integer a) {

        a=a+11;
        return print3(a);
    }


    public static int print3(Integer a) {
        a=a+67;
        return a + 65;
    }
}
