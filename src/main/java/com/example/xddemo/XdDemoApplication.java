package com.example.xddemo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class XdDemoApplication {


    // 密码长度不少于8位且至少包含大写字母、小写字母、数字和特殊符号中的四种
    //public static final String password1 = "^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{8,}$";
    // 密码长度8-20位且至少包含大写字母、小写字母、数字或特殊符号中的任意三种
    public static final String password = "^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\W_]+$)(?![a-z0-9]+$)(?![a-z\\W_]+$)(?![0-9\\W_]+$)[a-zA-Z0-9\\W_]{8,20}$";

    public static void main(String[] args) {
        String password1 = "ABCDEFGHIG";  //全部大写
        String password2 = "abcdefghig";  //全部小写
        String password3 = "0123456789";  //全部数字
        String password4 = "!@#$%^&*()";  //全部特殊字符
        String password5 = "ABCDEabcde";  //大写和小写
        String password6 = "ABCDE01234";  //大写和数字
        String password7 = "ABCDE!@#$%";  //大写和特殊字符
        String password8 = "abcde01234";  //小写和数字
        String password9 = "abcde!@#$%";  //小写字母和特殊字符
        String password10 = "01234!@#$%"; //数字和特殊字符
        String password11 = "Aa4!";       //长度不够8位数
        String password12 = "ABCDE01234!@#$%"; //符合要求密码任意三种
        String password13 = "ABCDEabcde!@#$%"; //符合要求密码任意三种
        String password14 = "ABCDEabcde01234"; //符合要求密码任意三种
        String password15 = "abcde01234!@#$%"; //符合要求密码任意三种
        String password16= "ABCabc012@#"; //符合要求密码任意三种 和 符合全部的四种


        String password = "yj123456";
        System.out.println(checkConditions(password3));


    }


    public static boolean checkConditions(String str) {
        if (str.length() < 8) {
            return false;
        }
        int conditionsMet = 0;
        // 条件1：字符串包含大写字母或者小写字母
        if (str.matches(".*[a-z].*") || str.matches(".*[A-Z].*")) {
            conditionsMet++;
        }
        // 条件2：字符串包含数字
        if (str.matches(".*\\d.*")) {
            conditionsMet++;
        }

        // 条件3：字符串包含特殊字符
        if (str.matches(".*[^a-zA-Z0-9].*")) {
            conditionsMet++;
        }

        // 至少满足两个条件即为正确
        return conditionsMet >= 2;
    }

}
