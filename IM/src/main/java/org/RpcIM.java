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

}
