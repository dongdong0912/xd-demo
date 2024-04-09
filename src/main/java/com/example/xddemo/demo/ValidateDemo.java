package com.example.xddemo.demo;

import lombok.Data;

import javax.validation.constraints.Min;

/**
 * Author: xuedong
 * Date: 2024/4/9
 */
public class ValidateDemo {


    public static void main(String[] args) {




        Person  person=new Person();


        Person1  person1=new Person1();







    }



    /**
     * 普通模式
     */
    public void test_vo2dto01(Person person) {

        Person1  person1=new Person1();
        person1.setName(person.getName());
        person1.setAge(person.getAge());
        person1.setVal(person.getVal());
        person1.setA(person.getB());


    }







    @Data
    static class Person1 {

        private String name;


        @Min(value = 1, message = "用药天数必须大于等于1")
        private Integer age;


        private Float val;



        private String a;
    }


    @Data
    static class Person {

        private String name;


        @Min(value = 1, message = "用药天数必须大于等于1")
        private Integer age;


        private Float val;


        private String b;
    }
}
