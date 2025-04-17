package com.example.xddemo;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author xuedong
 * Date: 2025/3/7
 */
public class TestDemo1 {


    public static void main(String[] args) {





        String s1 = "48924, 46610, 59852, 59557, 59756, 59895, 61965, 59756, 48845, 73379, 73383, 73386, 73385";
        String s2 = "9354, 61079, 61904, 26016, 73382, 73377, 73375, 73397, 73396, 73390";
        String s3 = "9362, 61847, 61970, 48831, 73382, 73378, 73374, 73384, 76107";
        String s4 = "48833, 9042, 59854, 59853, 59849, 26018, 48836, 73380, 73376";
        String s5 = "14890, 59621, 61944, 62016, 62241, 73381, 73395, 73394, 73389, 73387, 76110";

        String s6 = "56695, 25986, 8999, 61898, 61899, 61907, 55419, 73398, 73393, 73392";
        String s7 = "13342, 59925, 62242, 61907, 73392, 73391, 76109, 76108,73388";


        List<String> strings = Lists.newArrayList(s1, s2, s3, s4, s5, s6, s7);

        String s = String.join(",", strings);


        String[] split1 = s.split(",");
        System.out.println(split1.length);


        String[] split = s.split(",");

        List<String> list = Lists.newArrayList();
        for (String s11 : split) {
            String generate = generate(s11);
            list.add(generate);
        }

        String join = String.join(",", list);

        System.out.println(join);


    }


    private static String generate(String content) {
        return String.format("'%s'", content.trim());
    }
}
