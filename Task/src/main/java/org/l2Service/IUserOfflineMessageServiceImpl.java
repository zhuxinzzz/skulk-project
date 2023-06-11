package org.l2Service;

import org.game.skulk.Task.IUserOfflineMessageService;
import org.l9.dbagent.ManageOfflineMessages;

/**
 * @author zzz
 * @Date 10/06/2023
 */
public class IUserOfflineMessageServiceImpl implements IUserOfflineMessageService {
    @Override
    public String getUserOfflineMessage(String userName) {
        ManageOfflineMessages manageOfflineMessages = new ManageOfflineMessages();
        return manageOfflineMessages.getUserOfflineMessage(userName);
    }

    @Override
    public String writeRedisUserOfflineMessageRecord(String userName, String record) {
        return null;
    }
}
