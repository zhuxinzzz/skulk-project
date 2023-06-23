package org;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.game.skulk.api.DBAgent.IMessageStoreService;
import org.game.skulk.api.DBAgent.IUserOfflineMessageQueryService;
import org.serviceImpl.IMessageStoreServiceImpl;
import org.serviceImpl.UserOfflineMessageQueryServiceImpl;

import java.util.concurrent.CountDownLatch;

/**
 * @author zzz
 * @Date 19/06/2023
 */
public class RpcDBAgent {
    ApplicationConfig applicationConfig = new ApplicationConfig("dubbo-api-DBAgent-provider");
    RegistryConfig registryConfig = new RegistryConfig("zookeeper://s1:2181");


    /**
     * publish all dubbo service.
     */
    public void publishAllServices() {
        new Thread(this::publishQueryUserOfflineMessageRecordService).start();
        new Thread(this::publishProvidesMessageStoreFilenameService).start();

        System.out.println("dubbo service started......");

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * publish dubbo service
     *
     * @see org.serviceImpl.UserOfflineMessageQueryServiceImpl
     */
    public void publishQueryUserOfflineMessageRecordService() {
        //rpc serviceConfig provider
        ServiceConfig<IUserOfflineMessageQueryService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(IUserOfflineMessageQueryService.class);
        serviceConfig.setRef(new UserOfflineMessageQueryServiceImpl());

        serviceConfig.setApplication(applicationConfig);
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.export();

        System.out.println("user offline message query. dubbo service started......");
        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * publish dubbo service.
     *
     * @see org.serviceImpl.IMessageStoreServiceImpl
     */
    void publishProvidesMessageStoreFilenameService() {
        ServiceConfig<IMessageStoreService> service = new ServiceConfig<>();
        service.setInterface(IMessageStoreService.class);
        service.setRef(new IMessageStoreServiceImpl());
        service.setApplication(applicationConfig);
        service.setRegistry(registryConfig);
        service.export();
//        logger.info("dubbo service started......");

        System.out.println("get message store file .dubbo service started......");
        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
