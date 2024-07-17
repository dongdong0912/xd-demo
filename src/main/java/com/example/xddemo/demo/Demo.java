package com.example.xddemo.demo;

/**
 * Author: xuedong
 * Date: 2024/7/16
 */
public class Demo {


    public static void main(String[] args) {


        /**
         *
         */
        int a = 2;

        a = print1(a);

        System.out.println(a);


        a = print2(a);

        System.out.println(a);

        a = print3(a);

        System.out.println(a);
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
