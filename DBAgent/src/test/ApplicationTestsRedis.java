import com.AppDBAgent;
import com.dao.redis.L1Api;
import com.dao.redis.L2Services;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zzz
 * @Date 07/06/2023
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppDBAgent.class)
//@Transactional
public class ApplicationTestsRedis {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
//    @Autowired
//    public RedisTemplate<String, Object> redisTemplate;
    @Test
//    @Rollback
    public void writeAStringTypeKeyValuePair() {
        String key1 = "key1";
        String value1 = "value1";

        stringRedisTemplate.opsForValue().set(key1,value1);
        String value1Return = stringRedisTemplate.opsForValue().get(key1);
        Assert.assertEquals(value1,value1Return);

//        String userOffline1 = null;
//        Object o = null;
//        redisTemplate.opsForValue().set(userOffline1, o);

    }

    @Autowired
    L1Api l1Api;
    @Test
    public void l1TheUserHasNoOfflineMessageRecords(){
        String userOfflineMessage = l1Api.getUserOfflineMessage("user1");

        Assert.assertEquals("null", userOfflineMessage);
    }
    @Test
    public void l1UserHasOfflineMessageRecords(){
        String userOfflineMessage = l1Api.getUserOfflineMessage("user1");

        Assert.assertEquals("行号-文件名", userOfflineMessage);
    }

    @Autowired
    L2Services l2Services;
    @Test
    public void l2WriteOfflineMessageRecords() {
        l2Services.writeUserOfflineMessageRecord("user1","行号-文件名");
    }

}
