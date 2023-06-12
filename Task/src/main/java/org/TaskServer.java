package org;

import org.apache.dubbo.config.*;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.game.skulk.Task.IUserOfflineMessageService;
import org.junit.Test;
//import org.l2Service.IUserOfflineMessageQueryServiceImpl;
import org.l2Service.Task;
import org.l2Service.serviceImlp.UserOfflineMessageServiceImpl;

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
        TaskServer taskServer = new TaskServer();
        /*发布接口*/
        taskServer.publishCheckOfflineMessageFunction();
        /*消费mq的消息*/
//        taskServer.consumerMessagesAreWrittenToTheFileSystem();
    }
    /*应用配置*/
    public static ApplicationConfig applicationConfig = new ApplicationConfig();
    /*注册中心配置*/
    public static RegistryConfig registryConfig = new RegistryConfig();
    /*协议配置*/
    public static ProtocolConfig protocolConfig = new ProtocolConfig();
    static {
        /**/
        applicationConfig.setName("dubbo-api-Task-providerAndConsumer");
        /**/
        registryConfig.setAddress("zookeeper://s1:2181");
        /**/
        protocolConfig.setName("dubbo");
    }

    @Test
    public void testReleaseService() {
        TaskServer taskServer = new TaskServer();
        taskServer.publishCheckOfflineMessageFunction();
    }

    void publishCheckOfflineMessageFunction() {
        new Thread(() -> publishCheckOfflineMessageFunction1()).run();
    }

    void publishCheckOfflineMessageFunction1() {
        //rpc service provider

        protocolConfig.setPort(20889);
        /*服务配置*/
        ServiceConfig<IUserOfflineMessageService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(IUserOfflineMessageService.class);
        serviceConfig.setRef(new UserOfflineMessageServiceImpl());
        serviceConfig.setApplication(applicationConfig);
        serviceConfig.setRegistry(registryConfig);

        serviceConfig.setProtocol(protocolConfig);
        serviceConfig.export();

//        try {
//            new CountDownLatch(1).await();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }

    void consumerMessagesAreWrittenToTheFileSystem() {
        new Thread(() -> consumerMessagesAreWrittenToTheFileSystem1()).run();
    }

    void consumerMessagesAreWrittenToTheFileSystem1() {
        Properties props = new Properties();
//		props.put("bootstrap.servers", "slave1:9092,slave2:9092,slave3:9092");
        props.put("bootstrap.servers", "s1:9092");
        //每个消费者分配独立的组号
        props.put("group.id", "test");
        //如果value合法，则自动提交偏移量
        props.put("enable.auto.commit", "true");
        //设置多久一次更新被消费消息的偏移量
        props.put("auto.commit.interval.ms", "1000");
        //设置会话响应的时间，超过这个时间kafka可以选择放弃消费或者消费下一条消息
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
//        props.setProperty("key.deserializer",
//                "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("messagePersistence"));
        System.out.println("Subscribed to topic " + "messagePersistence");

//        int i = 0;
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s\n",
                        record.offset(), record.key(), record.value());
                //write fs
                Task.AppendContentToTheMessageFileOfTheRemoteMachine(record.key(), record.value());
            }
        }

    }


}
