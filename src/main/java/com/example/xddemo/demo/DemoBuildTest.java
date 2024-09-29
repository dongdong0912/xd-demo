package com.example.xddemo.demo;

import lombok.Data;

/**
 * Author: xuedong
 * Date: 2024/9/5
 */
@Data
public class DemoBuildTest {


    private String name;


    private String address;


    public static class Builder {

        private Integer age;
        private String phone;


        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder age(Integer age) {
            this.age = age;
            return this;
        }

        public DemoBuildTest build() {
            return new DemoBuildTest();
        }
    }
}
