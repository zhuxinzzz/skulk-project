package org;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author zzz
 * @Date 20/06/2023
 */
public class MqIM {


    void saveOfflineMessages(String fileName, String content) {
        Properties props = new Properties();
        //broker地址
//		props.put("bootstrap.servers", "slave1:9092,slave2:9092,slave3:9092");
        props.put("bootstrap.servers", "s1:9092");
        //请求的时候需要验证
        props.put("acks", "all");
        //请求失败需要重试jps
        props.put("retries", "0");
        //内存缓存区大小
        props.put("buffer.memory", 33554432);
        //指定消息key序列化方式
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        //指定消息本身的序列化方式
        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 2; i++) {
            producer.send(new ProducerRecord<String, String>
                    ("messagePersistence", "user1.bak", "line1\nline2"));
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        producer.close();
    }
}
