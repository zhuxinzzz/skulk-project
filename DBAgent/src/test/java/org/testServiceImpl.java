package org;

import org.junit.Assert;
import org.junit.Test;
import org.serviceImpl.IMessageStoreServiceImpl;

/**
 * @author zzz
 * @Date 20/06/2023
 */
public class testServiceImpl {
    IMessageStoreServiceImpl iMessageStoreService = new IMessageStoreServiceImpl();

    @Test
    public void test() {
        String fileName = iMessageStoreService.getUserOfflineMessageStorageFile("user1");
        Assert.assertEquals("user1|user2.bak", fileName);

    }


}
