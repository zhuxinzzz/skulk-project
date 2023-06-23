package org;

import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.l2Service.Task;
import org.messageServer.pojo.ClientMessage;
import org.pojo.MessageLine;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @author zzz
 * @Date 19/06/2023
 */
public class MqTask {
    public void startTheConsumerToWriteTextToTheFilesystem() {
        new Thread(() -> consumerMessagesAreWrittenToTheFileSystem1()).run();
    }

    public void consumerMessagesAreWrittenToTheFileSystem1() {
        Properties props = new Properties();
//		props.put("bootstrap.servers", "slave1:9092,slave2:9092,slave3:9092");
        props.put("bootstrap.servers", "s1:9092");
        //每个消费者分配独立的组号
        props.put("group.id", "g1");
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
        /*saveAMessageToAFile*/
        consumer.subscribe(Collections.singletonList("saveFile"));
        System.out.println("Subscribed to topic " + " ");

//        int i = 0;
//        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                Gson gson = new Gson();
                ClientMessage clientMessage = gson.fromJson(record.value(), ClientMessage.class);
                System.out.println("receive message from kafka");
                System.out.printf("offset = %d, key = %s, value = %s\n",
                        record.offset(), record.key(), clientMessage);
                System.out.println(clientMessage.toString());
                String fromUserId = clientMessage.getFromUserId();
                System.out.println("fromUserId:" + fromUserId);

                RpcTask rpcTask = new RpcTask();
                /*从DBAgent，获取文件名。*/
                String fileName = rpcTask.getUserOfflineMessageStorageFile(fromUserId);
                System.out.println("fileName:" + fileName);

                MessageLine messageLine = new MessageLine(clientMessage.getDate(), clientMessage.getToUserId(),
                        clientMessage.getFromUserId(), clientMessage.getContent());
                System.out.println(messageLine.toString());
                /*write fs*/
                Task.AppendContentToTheMessageFileOfTheRemoteMachine(fileName, messageLine.toString());

            }
        }

    }


}
