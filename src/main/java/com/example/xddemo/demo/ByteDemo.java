package com.example.xddemo.demo;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.List;

/**
 * Author: xuedong
 * Date: 2023/12/15
 */
public class ByteDemo {

    public static void main(String[] args) {

        VData vData = new VData();

        byte[] data1 = {0X3B, 0X09, 0X01, 0X02, 0X03, 0X04, 0X05, 0X53, 0X0A};
        byte[] data2 = {0X3B, 0X06, (byte) 0XAA, (byte) 0XBB, (byte) 0XA6, 0X0A};
        byte[] data3 = {0X3B, 0X0B, 0X01, 0X02, 0X03, 0X04, 0X05, (byte) 0XAA, (byte) 0XBB, (byte) 0XBA, 0X0A};

        System.out.println("发送数据：" + Arrays.toString(data1));
        for (byte b : data1) {
            vData.buffData(b);
        }


        System.out.println("发送数据：" +Arrays.toString(data2));
        for (byte b : data2) {
            vData.buffData(b);
        }

        System.out.println("发送数据：" +Arrays.toString(data3));
        for (byte b : data3) {
            vData.buffData(b);
        }


        byte[] data4 = concat(concat(data1,data2),data3);
        System.out.println("黏包发送："+Arrays.toString(data4));
        for (byte b : data4) {
            vData.buffData(b);
        }


        byte[] data5 = concat(concat(new byte[]{0X03, 0X04, 0X05, 0X53, 0X0A},data2),data3);
        System.out.println("错误发送："+Arrays.toString(data5));
        for (byte b : data5) {
            vData.buffData(b);
        }


        byte[] data6 = concat(concat(data1,new byte[]{0X03, 0X04, 0X05, 0X53, 0X0A}),data3);
        System.out.println("错误发送2："+Arrays.toString(data6));
        for (byte b : data6) {
            vData.buffData(b);
        }


        byte[] outBuff = vData.getOutBuff();
        List<byte[]> outBuffList = vData.getOutBuffList();
        for (byte[] bytes:outBuffList){
            outBuffList.remove(bytes);
        }
        System.out.println("收到数据2：" + JSON.toJSONString(outBuffList));


    }


    static byte[] concat(byte[] a, byte[] b) {
        byte[] c= new byte[a.length+b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }
}
