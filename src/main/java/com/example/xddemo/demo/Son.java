package com.example.xddemo.demo;

import lombok.Getter;
import lombok.Setter;

/**
 * Author: xuedong
 * Date: 2024/4/30
 */
@Setter
@Getter
public  abstract class Son extends Father {


    private String userName;


    public void test() {
        testFather3();
    }

   public abstract void testAb();


}
