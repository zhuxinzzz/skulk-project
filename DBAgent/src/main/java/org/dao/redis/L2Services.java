package org.dao.redis;


import org.dao.utils.RedisUtils;

import java.util.Objects;

/**
 * @author zzz
 * @Date 07/06/2023
 */
public class L2Services {
    /*redis_key_prefix*/
    /*用户离线消息*/
    public static final String PREFIX_USER_OFFLINE_MESSAGE_RECORD = "userOfflineMessage";
    /*key_separator，分隔符默认用'-'*/
    public static final String SEPARATOR = "-";


    L9ToRedis l9ToRedis = new L9ToRedis();

    public String getUserOfflineMessageRecords(String userName) {
        String userOfflineMessageRecord = l9ToRedis.get(
                PREFIX_USER_OFFLINE_MESSAGE_RECORD + SEPARATOR + userName);
        if (Objects.isNull(userOfflineMessageRecord)) {
            return userOfflineMessageRecord = "null";
        }
        return userOfflineMessageRecord;
    }

    /*userOfflineMessage-RecordsUser1,行号-文件名*/
    public void writeUserOfflineMessageRecord(String userName, String lineNumberAndFileName) {
        l9ToRedis.set(
                PREFIX_USER_OFFLINE_MESSAGE_RECORD + SEPARATOR + userName,
                lineNumberAndFileName);
    }
}
