package org.serviceImpl;

import org.dao.redis.L2Services;
import org.game.skulk.api.DBAgent.IMessageStoreService;

import java.util.Objects;

/**
 * @author zzz
 * @Date 19/06/2023
 */
public class IMessageStoreServiceImpl implements IMessageStoreService {

    @Override
    public String getUserOfflineMessageStorageFile(String userName) {
//        return "test";
        L2Services l2Services = new L2Services();
        String fileName = l2Services.getUserOfflineMessageStorageFile(userName);
        if (Objects.isNull(fileName)) {
            return "nullMessage";
        }
        return fileName;
    }
}
