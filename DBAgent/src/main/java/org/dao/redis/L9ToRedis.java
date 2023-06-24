package org.dao.redis;

import org.dao.utils.RedisUtils;
import redis.clients.jedis.Jedis;

/**
 * @author zzz
 * @Date 07/06/2023
 */
/*
 * 对redisTemplate api的再封装
 * */
public class L9ToRedis {
    //    StringRedisTemplate stringRedisTemplate;
    public void set(String key, String value) {
        RedisUtils.jedisPool.getResource().set(key, value);
//        RedisUtlis.JEDIS.set(key, value);
//        stringRedisTemplate.opsForValue().set(key, value);
    }



    public String get(String key) {/*20-1+2.bak*/
        String s = null;
        try {
            Jedis jedis = RedisUtils.jedisPool.getResource();
            s = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return s;
//        return RedisUtlis.JEDIS.get(key);
//        return stringRedisTemplate.opsForValue().get(key);
    }

}
