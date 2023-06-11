package org.l2Service;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.game.skulk.Task.IUserOfflineMessageService;

import java.util.concurrent.CountDownLatch;

/**
 * @author zzz
 * @Date 10/06/2023
 */
public class Services {
    void getUserOfflineMessage() {
        //rpc service provider
        /*设置服务提供方，提供的方法实例的配置类*/
        ServiceConfig<IUserOfflineMessageService> service = new ServiceConfig<>();
        service.setInterface(IUserOfflineMessageService.class);
        service.setRef(new IUserOfflineMessageServiceImpl());

        service.setApplication(new ApplicationConfig("dubbo-api-DBAgent-provider"));
        service.setRegistry(new RegistryConfig("zookeeper://s1:2181"));
        service.export();

        try {
            new CountDownLatch(1).await();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
