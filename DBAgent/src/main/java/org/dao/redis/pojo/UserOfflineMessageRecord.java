package org.dao.redis.pojo;

import lombok.Data;

/**
 * @author zzz
 * @Date 16/06/2023
 */
@Data
public class UserOfflineMessageRecord implements IPojoRedis {
    /**/
    String userName;
    String lineNumberAndFileName;

    public UserOfflineMessageRecord(String userName, String lineNumberAndFileName) {
        this.userName = PREFIX_USER_OFFLINE_MESSAGE_RECORD+userName;
        this.lineNumberAndFileName = lineNumberAndFileName;
    }

    public UserOfflineMessageRecord(String userName) {
        this.userName = PREFIX_USER_OFFLINE_MESSAGE_RECORD+SEPARATOR+userName;
    }
}
