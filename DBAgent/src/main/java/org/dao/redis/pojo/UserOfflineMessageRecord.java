package org.dao.redis.pojo;

/**
 * @author zzz
 * @Date 16/06/2023
 */
public class UserOfflineMessageRecord implements IPojoRedis {
    /**/
    String userName;
    String lineNumberAndFileName;

    public UserOfflineMessageRecord(String userName, String lineNumberAndFileName) {
        this.userName = PREFIX_USER_OFFLINE_MESSAGE_RECORD+userName;
        this.lineNumberAndFileName = lineNumberAndFileName;
    }

}
