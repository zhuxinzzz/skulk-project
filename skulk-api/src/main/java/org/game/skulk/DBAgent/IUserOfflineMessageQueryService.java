package org.game.skulk.DBAgent;

/**
 * @author zzz
 * @Date 10/06/2023
 */
public interface IUserOfflineMessageQueryService {
//    public String getUserOfflineMessage(String userName);
    public String getUserOfflineMessageMessageRecord(String userName);
    public String writeRedisUserOfflineMessageRecord(String userName,String record);
}
