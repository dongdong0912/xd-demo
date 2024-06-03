package com.example.xddemo.demo;

import lombok.Getter;
import lombok.Setter;

/**
 * Date: 2024/4/30
 *
 * @author xuedong
 */
@Setter
@Getter
public class Father {


    private String name;

    private Integer age;


    void testFather() {

        System.out.println("默认");
    }


    private void testFather1() {

        System.out.println("private");
    }

    protected void testFather2() {

        System.out.println("protected");
    }


    public void testFather3() {

        System.out.println("protected");
    }
}
