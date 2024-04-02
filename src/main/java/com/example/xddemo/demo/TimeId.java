package com.example.xddemo.demo;
import java.lang.management.ManagementFactory;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

/**
 * 时间序列ID生成器(20位)<br>
 * 时间戳+循环计数+随机数+网络地址+进程号<br>
 * 优势：<br>
 * TimeId 是有序的（有序但不连贯）<br>
 * TimeId 使用了和 SnowflakeIdWorker 类似的时间戳算法，但是冲突的概率更低<br>
 * TimeId 与 UUID 相比，大小从36个符号减少到20个符号<br>
 * 劣势：<br>
 * TimeId 是字符串，相对 SnowflakeIdWorker（ 长整型） 占用更大的空间，但是比UUID紧凑
 */
public class TimeId {

    private static final int RADIX36 = 36;
    private static final int TIMESTAMP_LENGTH = 9;
    private static final int COUNTER_LENGTH = 5;
    private static final int MAC_LENGTH = 2;
    private static final int PID_LENGTH = 1;
    private static final int RANDOM_LENGTH = 3;
    private static final int COUNTER_MOD = computeRadix36Mod(COUNTER_LENGTH);
    private static final int MAC_MOD = computeRadix36Mod(MAC_LENGTH);
    private static final int PID_MOD = computeRadix36Mod(PID_LENGTH);
    private static final int RANDOM_MOD = computeRadix36Mod(RANDOM_LENGTH);
    private static final char ZERO_CHAR = '0';

    private static final String LOCALHOST_IPV4 = "127.0.0.1";
    private static final String ANYHOST_IPV4 = "0.0.0.0";
    private static final String BROADCAST_IPV4 = "255.255.255.255";
    private static final Pattern IPV4_PATTERN = Pattern.compile(//
            "^(2(5[0-5]{1}|[0-4]\\d{1})|[0-1]?\\d{1,2})(\\.(2(5[0-5]{1}|[0-4]\\d{1})|[0-1]?\\d{1,2})){3}$"//
    );

    /** 循环序列号 */
    private static final AtomicInteger NEXT_COUNTER = new AtomicInteger(new SecureRandom().nextInt());

    /** 此类用来创建随机数的随机数生成器 */
    private static class Holder {
        static final SecureRandom NUMBER_GENERATOR = new SecureRandom();
        static final long MAC_VALUE = getMac();
        static final long PID_VALUE = getPid();
    }

    /**
     * 构造函数
     */
    protected TimeId() {
    }

    /**
     * 生成 TimeId
     * @return TimeId 字符串
     */
    public static String nextId() {
        StringBuilder buffer = new StringBuilder();
        append(buffer, timeGen(), TIMESTAMP_LENGTH);
        append(buffer, Math.abs(NEXT_COUNTER.getAndIncrement() % COUNTER_MOD), COUNTER_LENGTH);
        append(buffer, Math.abs(Holder.MAC_VALUE % MAC_MOD), MAC_LENGTH);
        append(buffer, Math.abs(Holder.PID_VALUE % PID_MOD), PID_LENGTH);
        append(buffer, Math.abs(Holder.NUMBER_GENERATOR.nextLong() % RANDOM_MOD), RANDOM_LENGTH);
        return buffer.toString();
    }

    /**
     * 将数值添加到字符串缓冲中
     * @param buffer 字符串缓冲中
     * @param value 数值
     * @param length 添加的位数
     */
    private static void append(StringBuilder buffer, long value, int length) {
        buffer.append(leftPad(Long.toString(value, RADIX36), length, ZERO_CHAR));
    }

    /**
     * 返回以毫秒为单位的当前时间
     * @return 当前时间(毫秒)
     */
    private static long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * 获得MAC值
     * @return MAC值
     */
    private static long getMac() {
        long mac = 0;
        try {
            byte[] address = getHardwareAddress();
            mac = new BigInteger(address).longValue();
        } catch (Exception | Error e) {
            mac = Holder.NUMBER_GENERATOR.nextLong();
        }
        return mac;
    }

    /**
     * 获得当前进程ID
     * @return 进程ID
     */
    private static long getPid() {
        long pid = 0;
        String name = ManagementFactory.getRuntimeMXBean().getName();
        try {
            pid = Long.parseLong(name.split("@")[0]);
        } catch (Exception | Error e) {
            pid = Holder.NUMBER_GENERATOR.nextLong();
        }
        return pid;
    }

    /**
     * 计算模数
     * @param length 长度
     * @return 进制模(32)
     */
    private static int computeRadix36Mod(int length) {
        int value = 1;
        for (int i = 0; i < length; i++) {
            value *= RADIX36;
        }
        return value - 1;
    }

    /**
     * 左填充指定字符的字符串.
     * @param cs 需要填充的字符串
     * @param size 要填充到的大小
     * @param padChar 要填充的字符
     * @return 左填充字符串或原始字符串（如果不需要填充）
     */
    private static String leftPad(final CharSequence cs, final int size, final char padChar) {
        if (cs == null) {
            return null;
        }
        final int pads = size - cs.length();
        if (pads <= 0) {
            return cs.toString();
        }
        StringBuilder builder = new StringBuilder(size);
        for (int i = 0; i < pads; i++) {
            builder.append(padChar);
        }
        builder.append(cs);
        return builder.toString();
    }

    /**
     * 获得本机 MAC地址
     * @return 本机MAC地址
     */
    private static final byte[] getHardwareAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface networkInterface = en.nextElement();
                for (Enumeration<InetAddress> addrs = networkInterface.getInetAddresses(); addrs.hasMoreElements();) {
                    String ip = addrs.nextElement().getHostAddress();
                    if (isValidIPv4(ip)) {
                        return networkInterface.getHardwareAddress();
                    }
                }
            }
        } catch (SocketException e) {
            // Ignore
        }
        return new byte[0];
    }

    /**
     * 判断是否是有效IPv4地址
     * @param ip IP地址
     * @return 如果是有效IPv4地址返回true,否则返回false
     */
    private static boolean isValidIPv4(String ip) {
        return (ip != null //
                && !ANYHOST_IPV4.equals(ip) //
                && !LOCALHOST_IPV4.equals(ip) //
                && !BROADCAST_IPV4.equals(ip) //
                && IPV4_PATTERN.matcher(ip).matches());
    }

    public static void main(String[] args) {
        // 获取全局唯一ID
        String id = TimeId.nextId();
        System.out.println(id);
    }
}
