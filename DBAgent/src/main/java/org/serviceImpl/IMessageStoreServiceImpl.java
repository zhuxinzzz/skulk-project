package org.serviceImpl;

import org.dao.redis.L2Services;
import org.game.skulk.api.DBAgent.IMessageStoreService;
import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

/**
 * @author zzz
 * @Date 19/06/2023
 */
public class IMessageStoreServiceImpl implements IMessageStoreService {
    @Test
    public void test() {

        String fileName = getUserOfflineMessageStorageFile("user1");
        Assert.assertEquals("user1|user2.bak", fileName);

    }

    @Override
    public String getUserOfflineMessageStorageFile(String userName) {
//        return "test";
        // TODO Auto-generated method stub
        L2Services l2Services = new L2Services();
        String fileName = l2Services.getUserOfflineMessageStorageFile(userName);
        if (Objects.isNull(fileName)) {
            return "nullMessage";
        }
        return fileName;
    }
}
