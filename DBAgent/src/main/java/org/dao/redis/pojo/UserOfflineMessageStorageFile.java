package org.dao.redis.pojo;

import lombok.Data;

/**
 * @author zzz
 * @Date 19/06/2023
 */

public class UserOfflineMessageStorageFile implements IPojoRedis{
    String keyUserName;
    String valueFileName;

    public UserOfflineMessageStorageFile(String keyUserName, String valueFileName) {
        this.keyUserName = PREFIX_USER_MESSAGE_STORAGE_FILE_NAME + SEPARATOR + keyUserName;
        this.valueFileName = valueFileName;
    }

    public UserOfflineMessageStorageFile(String userName) {
        this.keyUserName = PREFIX_USER_MESSAGE_STORAGE_FILE_NAME + SEPARATOR + userName;
    }

    public String getKeyUserName() {
        return keyUserName;
    }

    public String getValueFileName() {
        return valueFileName;
    }
}
