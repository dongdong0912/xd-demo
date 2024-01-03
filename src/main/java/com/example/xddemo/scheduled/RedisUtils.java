package com.example.xddemo.scheduled;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedisUtils {


    private static StringRedisTemplate stringRedisTemplate;

    private static RedisTemplate redisTemplate;

    public static final String DEFAULT_CREDIT_LOCK_KEY = "cic-integration-web";

    @Autowired
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        RedisUtils.stringRedisTemplate = redisTemplate;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisUtils.redisTemplate = redisTemplate;
    }

    /**
     * 删除指定的key
     *
     * @param key
     */
    public static void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    public static void unLock(String key) {
        delete(key);
    }

    public static void unLock() {
        delete(DEFAULT_CREDIT_LOCK_KEY);
    }

    /**
     * 检测当前redis是否锁住
     *
     * @return true 已锁住
     * false 未锁住
     */
    public static boolean checkLock() {
        return get(RedisUtils.DEFAULT_CREDIT_LOCK_KEY) != null;
    }

    /**
     * 获取锁，默认5s
     * @param key
     * @return
     */
    public static boolean tryLock(String key) {
        return tryLock(key, 5);
    }

    /**
     * 获取锁,设置锁过期时间,如果没有释放自动过期 setnx 当key存在时,不能设置值,返回false。
     * 由于redis是单线程保证了不会产生并发,当并发发生时保证了只有一个线程可以执行
     * 默认10秒内
     * @param key
     * @return false 获取锁失败
     */
    public static boolean tryLock(String key, int timeout) {
        return stringRedisTemplate.execute((RedisConnection connection) -> {
            byte[] lockBytes = stringRedisTemplate.getStringSerializer().serialize(key);
            boolean locked = connection.setNX(lockBytes, lockBytes);
            connection.expire(lockBytes, timeout);//
            return locked;
        });
    }


    /******** redis 字符串操作  *******/

    /**
     * 设置指定key的值
     *
     * @param key
     * @param value
     */
    public static void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public static void setEx(String key, String value, long time, TimeUnit timeUnit) {
        stringRedisTemplate.opsForValue().set(key, value, time, timeUnit);
    }


    public static void setObjectEx(String key, Object value, long time, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, time, timeUnit);
    }


    /**
     * 获取指定key的值
     *
     * @param key
     * @return
     */
    public static String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 获取指定key的值
     *
     * @param key
     * @return
     */
    public static Object getObject(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public static boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }




    /******** redis hash结构操作  *******/

    /**
     * 获取存储在指定哈希表中指定字段的值
     *
     * @param key
     * @param field
     * @return
     */
    public static Object hGet(String key, String field) {
        return stringRedisTemplate.opsForHash().get(key, field);
    }

    /**
     * 获取存储在指定哈希表中所有集合
     *
     * @param hashKey
     * @return
     */
    public static Map hGetAll(String hashKey) {
        return stringRedisTemplate.opsForHash().entries(hashKey);
    }

    public static Set hGetKeys(String hashKey) {
        return stringRedisTemplate.opsForHash().keys(hashKey);
    }

    public static List hGetValues(String hashKey) {
        return stringRedisTemplate.opsForHash().values(hashKey);
    }


    /**
     * 设置指定哈希表中的hashKey的值
     *
     * @param hashKey
     * @param field
     * @param value
     */
    public static void hPut(String hashKey, String field, String value) {
        stringRedisTemplate.opsForHash().put(hashKey, field, value);
    }

    public static void hPutAll(String hashKey, Map<String, String> fieldMaps) {
        stringRedisTemplate.opsForHash().putAll(hashKey, fieldMaps);
    }

    /**
     * 设置指定hashKey中的field的值自增指定的数量，自增数量类型是整形
     * 如果该field不存在，则会先重新创建并被赋值为0开始计算。
     *
     * @param hashKey
     * @param field
     * @param increment
     * @return Long 返回相加后的值
     */
    public static Long hIncrBy(String hashKey, String field, long increment) {
        return stringRedisTemplate.opsForHash().increment(hashKey, field, increment);
    }

    /**
     * 设置指定hashKey中的field的值自增指定的数量，自增数量类型是双精度浮点型
     * 如果该field不存在，则会先重新创建并被赋值为0开始计算。
     *
     * @param hashKey
     * @param field
     * @param increment
     * @return Long 返回相加后的值
     */
    public static Double hIncrBy(String hashKey, String field, double increment) {
        return stringRedisTemplate.opsForHash().increment(hashKey, field, increment);
    }


    /**
     * 运行lua脚本，返回指定数据类型的值
     *
     * @param luaScript   lua脚本字符串
     * @param returnClass 返回值类型
     * @param keys        替换lua脚本中的KEYS[N]，N从1开始，对应ArrayList中索引为0的元素值
     * @param args        替换lua脚本中ARGV[N]的值
     * @param <T>
     * @return
     */
    public static <T> T executeLuaScript(String luaScript, Class<T> returnClass, List<String> keys, Object... args) {
        RedisScript<T> redisScript = new DefaultRedisScript<>(luaScript, returnClass);
        return stringRedisTemplate.execute(redisScript, keys, args);
    }

    /**
     * 开启redis流水线，对循环中的redis操作合并成一次命令发送给redis服务器，节省网络开销
     *
     * @param callback
     * @param <T>
     * @return
     */
    public static <T> List<Object> executePipeLined(RedisCallback<T> callback) {
        return stringRedisTemplate.executePipelined(callback);
    }

}

