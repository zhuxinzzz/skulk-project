package org;

import org.junit.Test;

/**
 * @author zzz
 * @Date 20/06/2023
 */
public class testRpc {
    RpcIM rpcIM = new RpcIM();
    @Test
    public void testRpcToGetOfflineMessagesFromTaskServer() {
        /*获取用户离线消息*/
        rpcIM.getUserOfflineMessageRecords("user1");
        for (; ; ) {

        }
    }
}
