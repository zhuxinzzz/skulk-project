package org.serviceImpl;


import org.dao.redis.L2Services;
import org.game.skulk.DBAgent.IUserOfflineMessageQueryService;

/**
 * @author zzz
 * @Date 07/06/2023
 */
public class L1Api implements IUserOfflineMessageQueryService {
    L2Services l2Services=new L2Services();

    public String getUserOfflineMessage(String userName){
        String userOfflineMessage = l2Services.getUserOfflineMessageRecords(userName);

        return userOfflineMessage;
    }
    public String writeRedisUserOfflineMessageRecord(String userName,String record){
        l2Services.writeUserOfflineMessageRecord(userName,record);

        return "ok";
    }

}
