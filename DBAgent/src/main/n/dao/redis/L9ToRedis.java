package comApp.dao.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author zzz
 * @Date 07/06/2023
 */
/*
* 对redisTemplate api的再封装
* */
@Component
public class L9ToRedis {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    //    @Bean
    public String get(String key) {/*20-1+2.bak*/
        return stringRedisTemplate.opsForValue().get(key);
    }
    public void set(String key,String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }
}
