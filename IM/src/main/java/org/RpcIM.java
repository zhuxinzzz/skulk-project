package org;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.config.utils.SimpleReferenceCache;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.game.skulk.api.Task.IUserOfflineMessageService;
import scala.App;

import java.util.Properties;

/**
 * @author zzz
 * @Date 20/06/2023
 */
public class RpcIM {
    ApplicationConfig applicationConfig = new ApplicationConfig("dubbo-api-Task-consumer");
    RegistryConfig registryConfig = new RegistryConfig("zookeeper://s1:2181");


    /*Get data from Task service.*/
    public void getUserOfflineMessageRecords(String userName) {
        ReferenceConfig<IUserOfflineMessageService> reference = new ReferenceConfig<>();
        reference.setInterface(IUserOfflineMessageService.class);

        DubboBootstrap bootstrap = DubboBootstrap.getInstance();
        bootstrap.application(applicationConfig)
                .registry(registryConfig)
                .reference(reference)
                .start();
        IUserOfflineMessageService service = SimpleReferenceCache.getCache().get(reference);

        String message = service.getUserOfflineMessage(userName);
        System.out.println(message);
    }

    public static void productionWritesChatFileMessages(String userName, String content) {
        productionWritesChatFileMessages1(userName, content);
//        new Thread(() -> productionWritesChatFileMessages1(userName, content)).start();
    }


    static void productionWritesChatFileMessages1(String userName, String content) {
        Properties props = new Properties();
        //broker地址
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
        /*messagePersistence*/
        producer.send(new ProducerRecord<String, String>(
                "saveFile", userName, content));
    }


}
