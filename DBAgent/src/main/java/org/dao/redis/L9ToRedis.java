package org.dao.redis;

import org.dao.utils.RedisUtils;

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
        RedisUtils.jedis.set(key, value);
//        RedisUtlis.JEDIS.set(key, value);
//        stringRedisTemplate.opsForValue().set(key, value);
    }

    public String get(String key) {/*20-1+2.bak*/
        return RedisUtils.jedis.get(key);
//        return RedisUtlis.JEDIS.get(key);
//        return stringRedisTemplate.opsForValue().get(key);
    }

}
