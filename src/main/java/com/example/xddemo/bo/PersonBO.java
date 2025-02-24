package com.example.xddemo.bo;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: xuedong
 * Date: 2024/2/27
 *
 * @author xuedong
 */
@Slf4j
@Data
public class PersonBO {


    @SneakyThrows
    public static void main(String[] args) {



        PersonBO personBO = new PersonBO();


        String s="1"+personBO;

        System.out.println(s);


    }


}



