package org.dao.redis.pojo;

/**
 * @author zzz
 * @Date 16/06/2023
 */
public interface IPojoRedis {
    /*key_separator，分隔符默认用'-'*/
    public static final String SEPARATOR = "-";

    /*redis_key_prefix*/
    /*前缀用户离线消息*/
    public static final String PREFIX_USER_OFFLINE_MESSAGE_RECORD = "userOfflineMessage";
    /*前缀用户基本信息*/
    public static final String PREFIX_BASIC_USER_INFORMATION = "userInfo";
    /*前缀用户消息文件名*/
    public static final String PREFIX_USER_MESSAGE_STORAGE_FILE_NAME = "userMessageStorageFile";


}
