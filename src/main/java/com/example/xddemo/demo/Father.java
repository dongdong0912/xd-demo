package com.example.xddemo.demo;

import lombok.Data;

/**
 * Date: 2024/4/30
 *
 * @author xuedong
 */
@Data
public class Father {


    private String name;

    public Integer age;

    String address;

    protected String phone;


    public static final String mobile = testInit();


    private String test1 = "薛栋111";


    public static String testInit() {
        System.out.println("初始化");
        return "薛栋";
    }

    void defaultFather() {

        System.out.println("默认");
    }


    private void testPrivateFather1() {

        System.out.println("private");
    }

    protected void testProtectedFather2() {

        System.out.println("protected");
    }


    public void testFather3() {

        System.out.println("protected");
    }
}
