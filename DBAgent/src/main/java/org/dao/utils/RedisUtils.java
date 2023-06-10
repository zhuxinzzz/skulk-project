package org.dao.utils;

import redis.clients.jedis.Jedis;

/**
 * @author zzz
 * @Date 10/06/2023
 */
public class RedisUtils {
    public   static Jedis jedis=new Jedis("s1",6379);
    public   RedisUtils instance=new RedisUtils();

    public static void main(String[] args) {
        RedisUtils.jedis.set("key1112", "value1112");
    }

}
