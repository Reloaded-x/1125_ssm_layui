package com.sy.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.*;
import java.util.Set;

/**
 * @ClassName RedisClient
 * @Description TODO
 * @Author Administrator
 * @Date: 2021/4/14 17:17
 * @Version 1.0
 */
public class RedisClient {
    private  static JedisPool jedisPool;
    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(200);
        config.setMaxWaitMillis(1000 * 100);
        config.setTestOnBorrow(false);
        jedisPool = new JedisPool(config, "127.0.0.1", 6379);
    }

    /**
     * 把对像保存到redis中
     * @param key
     * @param object
     */
    public static void setObject(String key,Object object){
        Jedis jedis = jedisPool.getResource();
        jedis.set(key.getBytes(), toBytes(object));
        jedis.close();
    }

    /**
     * 失效特定key
     */
    public static void invalidate(String key){
        Jedis jedis = jedisPool.getResource();
        jedis.del(key.getBytes());
        jedis.close();
    }

    /**
     * 失效以某串字符开始的key
     *  user
     * @param key
     */
    public static void invalidataStartWith(String key){
        String  pattern= key+"*";
        Jedis jedis = jedisPool.getResource();
        Set<byte[]> keys = jedis.keys(pattern.getBytes());
        for(byte[] k:keys){
            jedis.del(k);
        }
        jedis.close();
    }

    /**
     * 失效所有key
     */
    public static void invalidateAll(){
        Jedis jedis = jedisPool.getResource();
        jedis.flushDB();
        jedis.close();
    }

    /**
     * 从redis中读取对像
     * @param key
     * @return
     * @throws Exception
     */
    public static Object getObject(String key) throws Exception{
        Jedis jedis = jedisPool.getResource();
        byte[] bytes = jedis.get(key.getBytes());
        jedis.close();
        if(bytes==null){
            return null;
        }else {
            return toObject(bytes);
        }
    }

    // 序列化
    private static byte[] toBytes(Object object) {

        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;//把对像写入内存数组中
        byte[] bytes = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            bytes = baos.toByteArray();
        } catch (Exception e) {
            System.err.println("序列化失败" + e.getMessage());
        }
        return bytes;
    }

    // 反序列化
    private static Object toObject(byte[] bytes) throws IOException, ClassNotFoundException {

        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
        } catch (Exception e) {
            System.err.println("反序列化失败" + e.getMessage());
        }
        return ois.readObject();
    }
}
