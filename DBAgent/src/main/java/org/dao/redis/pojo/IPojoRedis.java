package org.dao.redis.pojo;

/**
 * @author zzz
 * @Date 16/06/2023
 */
public interface IPojoRedis {
    /*redis_key_prefix*/
    /*用户离线消息*/
    public static final String PREFIX_USER_OFFLINE_MESSAGE_RECORD = "userOfflineMessage";
    public static final String PREFIX_BASIC_USER_INFORMATION = "userInfo";

    /*key_separator，分隔符默认用'-'*/
    public static final String SEPARATOR = "-";


}
