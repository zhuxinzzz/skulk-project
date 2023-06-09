package com.dao.redis;


/**
 * @author zzz
 * @Date 07/06/2023
 */
public class L1Api {
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
