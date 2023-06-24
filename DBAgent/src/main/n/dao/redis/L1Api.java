package comApp.dao.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zzz
 * @Date 07/06/2023
 */
@Component
public class L1Api {
    @Autowired
    L2Services l2Services;
//    @Bean
    public String getUserOfflineMessage(String userName){
        String userOfflineMessage = l2Services.getUserOfflineMessageRecords(userName);

        return userOfflineMessage;
    }
    public String writeRedisUserOfflineMessageRecord(String userName,String record){
        l2Services.writeUserOfflineMessageRecord(userName,record);

        return "ok";
    }

}
