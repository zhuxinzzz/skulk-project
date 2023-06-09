package com.dao.redis;

/**
 * @author zzz
 * @Date 07/06/2023
 */
/*
* 对redisTemplate api的再封装
* */
public class L9ToRedis {
//    StringRedisTemplate stringRedisTemplate;
    public String get(String key) {/*20-1+2.bak*/
//        return stringRedisTemplate.opsForValue().get(key);
        return null;
    }
    public void set(String key,String value) {
//        stringRedisTemplate.opsForValue().set(key, value);
    }
}
