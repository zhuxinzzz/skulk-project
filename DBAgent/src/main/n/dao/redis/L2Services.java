package comApp.dao.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zzz
 * @Date 07/06/2023
 */
@Service
public class L2Services {
    /*redis_key_prefix*/
    /*用户离线消息*/
    public static final String PREFIX_USER_OFFLINE_MESSAGE_RECORD = "userOfflineMessage";
    /*key_separator，分隔符默认用'-'*/
    public static final String SEPARATOR = "-";
    @Autowired
    L9ToRedis l9ToRedis;
//    @Bean
    public String getUserOfflineMessageRecords(String userName) {
        String userOfflineMessageRecord = l9ToRedis.get(PREFIX_USER_OFFLINE_MESSAGE_RECORD+
                "-"
                +userName);
        if (userOfflineMessageRecord == null) {
            return userOfflineMessageRecord = "null";
        }
        return userOfflineMessageRecord;
    }
    /*userOfflineMessage-RecordsUser1,行号-文件名*/
    public void writeUserOfflineMessageRecord(String userName,String lineNumberAndFileName){
        l9ToRedis.set(
                PREFIX_USER_OFFLINE_MESSAGE_RECORD+ SEPARATOR + userName,
                lineNumberAndFileName);

    }
}
