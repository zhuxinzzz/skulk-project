package org.dao.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author zzz
 * @Date 10/06/2023
 */
public class RedisUtils {
    // 创建 JedisPoolConfig 对象并配置连接池
    static JedisPoolConfig poolConfig = new JedisPoolConfig();
    // 创建 JedisPool 对象并设置 Redis 服务器的连接信息
    public static JedisPool jedisPool = new JedisPool(poolConfig, "s1", 6379);

//    public RedisUtils() {
//        poolConfig.setMaxTotal(10); // 设置连接池的最大连接数
//        poolConfig.setMaxIdle(5); // 设置连接池的最大空闲连接数
//
//        jedisPool = new JedisPool(poolConfig, "s1", 6379);
//    }
//
//    public RedisUtils(JedisPoolConfig poolConfig) {
//        this.poolConfig = poolConfig;
//
//    }

    public void test() {
        // 从连接池中获取 Jedis 对象并执行 Redis 操作
        try (Jedis jedis = jedisPool.getResource()) {
            // 执行一些基本的 Redis 操作
            jedis.set("foo", "bar");
            String value = jedis.get("foo");
            System.out.println("Value of 'foo': " + value);
        }

        // 关闭连接池
        jedisPool.close();

    }

    //    public static Jedis jedis = new Jedis("s1", 6379);
//    public static Jedis jedis = jedisPool.getResource();

//    public   RedisUtils instance=new RedisUtils();
}
