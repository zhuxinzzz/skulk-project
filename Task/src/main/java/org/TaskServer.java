package org;

import com.google.gson.Gson;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.game.skulk.api.Task.IUserOfflineMessageService;
import org.junit.Test;
import org.l2Service.Task;
import org.l2Service.serviceImlp.UserOfflineMessageServiceImpl;
import org.messageServer.pojo.ClientMessage;
import org.pojo.MessageLine;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

/**
 * @author zzz
 * @Date 09/06/2023
 */

public class TaskServer {
    public static void main(String[] args) {
//        TaskServer taskServer = new TaskServer();
        /*发布接口*/
//        taskServer.publishCheckOfflineMessageFunction();
//        taskServer.publishAServiceForGettingOfflineMessages();
        /*消费mq的消息*/
//        taskServer.consumerMessagesAreWrittenToTheFileSystem();

    }

}
