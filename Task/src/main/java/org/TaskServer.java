package org;

/**
 * @author zzz
 * @Date 09/06/2023
 */

public class TaskServer {
    public static void main(String[] args) {
//        TaskServer taskServer = new TaskServer();
        /*发布接口*/
//        RpcTask rpcTask = new RpcTask();
//        rpcTask.publishAllServices();
        /*start consumer*/
        MqTask mqTask = new MqTask();
        mqTask.startTheConsumerToWriteTextToTheFilesystem();

//        taskServer.publishCheckOfflineMessageFunction();
//        taskServer.publishAServiceForGettingOfflineMessages();
        /*消费mq的消息*/
//        taskServer.consumerMessagesAreWrittenToTheFileSystem();

    }

}
