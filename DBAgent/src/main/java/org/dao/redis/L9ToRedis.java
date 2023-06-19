package org.dao.redis;

import org.dao.utils.RedisUtils;
import org.junit.Assert;
import org.junit.Test;

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

    @Test
    public void test() {
//        RedisUtils.jedis.set("test", "test");
        String s = RedisUtils.jedis.get("userMessageStorageFile-user1");

        System.out.println(s);

        Assert.assertEquals("user1|user2.bak", s);
    }

    public String get(String key) {/*20-1+2.bak*/
        return RedisUtils.jedis.get(key);
//        return RedisUtlis.JEDIS.get(key);
//        return stringRedisTemplate.opsForValue().get(key);
    }

}
