package com.example.xddemo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class XdDemoApplication {




    public static UUID generateUUID(long timestamp) {
        // 将指定时间戳转换成100纳秒为单位
        long time = (timestamp * 10000) + 0x01B21DD213814000L;

        // 生成随机数（这里使用随机数代替UUID中的节点和时钟序列部分）
        long node = (long) (Math.random() * 0x10000) + 0x8000; // 节点
        long clockSequence = (long) (Math.random() * 0x10000); // 时钟序列

        // 组合时间戳、节点和时钟序列，生成UUID
        long mostSigBits = (time << 32)
                | (node << 16)
                | clockSequence;
        long leastSigBits = (long) (Math.random() * 0x10000); // 最低64位随机数

        return new UUID(mostSigBits, leastSigBits);
    }

    public static void main(String[] args) {
        long timestamp = 20240319011234L;
        UUID uuid = generateUUID(timestamp);
        System.out.println("生成的基于时间戳的UUID：" + uuid.toString());
    }

}
