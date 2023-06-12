package org.game.skulk.Task;

/**
 * @author zzz
 * @Date 10/06/2023
 */
public interface IUserOfflineMessageService {
    public String getUserOfflineMessage(String userName);
    public String getUserOfflineMessageMessageRecord(String userName);
    public String writeRedisUserOfflineMessageRecord(String userName,String record);
}
