package com.example.xddemo.demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.Arrays;
import java.util.Objects;

/**
 * Author: xuedong
 * Date: 2024/1/24
 */
public class ByteBufDemo {

    public static void main(String[] args) {


        Integer aa=1000;
        Integer b=1000;
        boolean equals = Objects.equals(aa, b);



        System.out.println(b);


        int  a=0x12;
        System.out.println(a);
        // 创建一个ByteBuf
        ByteBuf byteBuf = Unpooled.buffer(10);

        // 写入一些数据
        byteBuf.writeBytes(new byte[]{1, 2, 3, 4, 5});

        // 标记当前读取位置
        byteBuf.markReaderIndex();


        byteBuf.writeInt(4);

        // 读取两个字节
        byte firstByte = byteBuf.readByte();
        byte secondByte = byteBuf.readByte();
        System.out.println("Read Bytes: " + firstByte + ", " + secondByte);

        // 进行一些其他操作，例如处理读取的数据

        // 重置读取位置到标记的位置
        byteBuf.resetReaderIndex();

        // 再次读取两个字节
        byte reReadFirstByte = byteBuf.readByte();
        byte reReadSecondByte = byteBuf.readByte();
        System.out.println("Re-read Bytes: " + reReadFirstByte + ", " + reReadSecondByte);


    }




    private static void extracted() {
        // 1.创建一个非池化的ByteBuf，大小为14个字节
        ByteBuf buffer = Unpooled.buffer(14);
        System.out.println("1.创建一个非池化的ByteBuf，大小为14个字节");
        System.out.println("ByteBuf空间大小：" + buffer.capacity());
        // 2.写入3个字节
        buffer.writeByte(62);
        buffer.writeByte(75);
        buffer.writeByte(67);
        System.out.println("\r\n2.写入3个字节");
        System.out.println("readerIndex位置：" + buffer.readerIndex());
        System.out.println("writerIndex位置：" + buffer.writerIndex());
        // 3.写入一段字节
        byte[] bytes = {73, 74, 61, 63, 0x6B};
        buffer.writeBytes(bytes);
        System.out.println("\r\n3.写入一段字节");
        System.out.println("readerIndex位置：" + buffer.readerIndex());
        System.out.println("writerIndex位置：" + buffer.writerIndex());
        // 4.读取全部内容
        byte[] allBytes = new byte[buffer.readableBytes()];
        buffer.readBytes(allBytes);
        System.out.println("\r\n4.读取全部内容");
        System.out.println("readerIndex位置：" + buffer.readerIndex());
        System.out.println("writerIndex位置：" + buffer.writerIndex());
        System.out.println("读取全部内容：" + Arrays.toString(allBytes));
        // 5.重置指针位置
        buffer.resetReaderIndex();
        System.out.println("\r\n5.重置指针位置");
        System.out.println("readerIndex位置：" + buffer.readerIndex());
        System.out.println("writerIndex位置：" + buffer.writerIndex());
        // 6.读取3个字节
        byte b0 = buffer.readByte();
        byte b1 = buffer.readByte();
        byte b2 = buffer.readByte();
        System.out.println("\r\n6.读取3个字节");
        System.out.println("readerIndex位置：" + buffer.readerIndex());
        System.out.println("writerIndex位置：" + buffer.writerIndex());
        System.out.println("读取3个字节：" + Arrays.toString(new byte[]{b0, b1, b2}));
        // 7.读取一段字节
        ByteBuf byteBuf = buffer.readBytes(5);
        byte[] dst = new byte[5];
        byteBuf.readBytes(dst);
        System.out.println("\r\n7.读取一段字节");
        System.out.println("readerIndex位置：" + buffer.readerIndex());
        System.out.println("writerIndex位置：" + buffer.writerIndex());
        System.out.println("读取一段字节：" + Arrays.toString(dst));
        // 8.丢弃已读内容
        buffer.discardReadBytes();
        System.out.println("\r\n8.丢弃已读内容");
        System.out.println("readerIndex位置：" + buffer.readerIndex());
        System.out.println("writerIndex位置：" + buffer.writerIndex());
        // 9.清空指针位置
        buffer.clear();
        buffer.writeByte(178);
        System.out.println("\r\n9.清空指针位置");
        System.out.println("readerIndex位置：" + buffer.readerIndex());
        System.out.println("writerIndex位置：" + buffer.writerIndex());


        ByteBuf buffer1 = Unpooled.buffer(5);
        buffer1.writeByte(4);
        buffer1.writeByte(5);
        buffer1.writeByte(6);
        System.out.println("writerIndex位置：" + buffer1.writerIndex());
        byte b = buffer1.readByte();
        buffer1.discardReadBytes();
        System.out.println("readerIndex位置：" + buffer1.readerIndex());
        byte bb = buffer1.readByte();
        System.out.println(bb);
        System.out.println("writerIndex位置：" + buffer1.writerIndex());
        System.out.println("readerIndex位置：" + buffer1.readerIndex());

        int i = buffer1.readableBytes();

        byte[] dst1 = new byte[i];
        buffer1.readBytes(dst1);
        System.out.println("读取一段字节：" + Arrays.toString(dst1));
    }
}
