package org.dao.redis;


import org.dao.redis.pojo.UserOfflineMessageRecord;
import org.dao.redis.pojo.UserOfflineMessageStorageFile;

import java.util.Objects;

/**
 * @author zzz
 * @Date 07/06/2023
 */
public class L2Services {
//    @Test
//    public void test() {
//        UserOfflineMessageStorageFile messageStorageFile =
//                new UserOfflineMessageStorageFile("user1", "user1|user2.bak");
//        setUserOfflineMessageStorageFile(messageStorageFile);
//        Assert.assertEquals("user1|user2.bak",
//                getUserOfflineMessageStorageFile("user1"));
//    }

    L9ToRedis l9ToRedis = new L9ToRedis();

//    public String setUserOfflineMessageStorageFile(UserOfflineMessageStorageFile storageFile) {
////        UserOfflineMessageStorageFile storageFile = new UserOfflineMessageStorageFile(userName, fileName);
//
//        l9ToRedis.set(
//                storageFile.getKeyUserName(),
//                storageFile.getValueFileName());
//
////        return storageFile.getLineNumberAndFileName();
//        return "ok";
//    }
//
    public String getUserOfflineMessageStorageFile(String userName) {
        UserOfflineMessageStorageFile storageFile =
                new UserOfflineMessageStorageFile(userName);
        String fileName = l9ToRedis.get(storageFile.getKeyUserName());

        return fileName;
    }

    public String getUserOfflineMessageRecords(String userName) {
        UserOfflineMessageRecord record = new UserOfflineMessageRecord(userName);

        String userOfflineMessageRecord = l9ToRedis.get(record.getUserName());

        if (Objects.isNull(userOfflineMessageRecord)) {
            return userOfflineMessageRecord = "nullMessage";
        }
        return userOfflineMessageRecord;
    }

    /*userOfflineMessage-RecordsUser1,行号-文件名*/
    public void writeUserOfflineMessageRecord(String userName, String lineNumberAndFileName) {
        UserOfflineMessageRecord record = new UserOfflineMessageRecord(userName, lineNumberAndFileName);

        l9ToRedis.set(
                record.getUserName(),
                record.getLineNumberAndFileName());
    }
}
