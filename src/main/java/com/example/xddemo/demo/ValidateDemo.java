package com.example.xddemo.demo;

import lombok.Data;

import javax.validation.constraints.Min;
import java.util.List;

/**
 * Author: xuedong
 * Date: 2024/4/9
 */
public class ValidateDemo {


    public static void main(String[] args) {




        Person  person=new Person();


        List<String> strings = ValidatorUtils.validateEntity(person);

        System.out.println(strings.toString());


    }





    @Data
    static class Person {

        private String name;


        @Min(value = 1, message = "用药天数必须大于等于1")
        private Integer age;


        private Float val;
    }
}
