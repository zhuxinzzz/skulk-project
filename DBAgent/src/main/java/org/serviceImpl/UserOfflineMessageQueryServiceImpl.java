package org.serviceImpl;


import org.dao.redis.L2Services;
import org.game.skulk.api.DBAgent.IUserOfflineMessageQueryService;

/**
 * @author zzz
 * @Date 07/06/2023
 */
public class UserOfflineMessageQueryServiceImpl implements IUserOfflineMessageQueryService {
    L2Services l2Services = new L2Services();

    @Override
    public String getUserOfflineMessageMessageRecord(String userName) {
        String userOfflineMessage = l2Services.getUserOfflineMessageRecords(userName);

        return userOfflineMessage;
    }

    @Override
    public String writeRedisUserOfflineMessageRecord(String userName, String record) {
        l2Services.writeUserOfflineMessageRecord(userName, record);

        return "ok";
    }

}
