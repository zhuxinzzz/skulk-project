package org.dao;

import org.dao.utils.RedisUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author zzz
 * @Date 20/06/2023
 */
public class testRedis {
    @Test
    public void test() {
//        RedisUtils.jedis.set("test", "test");
        String s = RedisUtils.jedisPool.getResource().get("userMessageStorageFile-user1");

        System.out.println(s);

        Assert.assertEquals("user1+user2.bak", s);
    }
}
