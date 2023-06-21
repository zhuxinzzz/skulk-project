package org;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.game.skulk.api.DBAgent.IUserOfflineMessageQueryService;
import org.serviceImpl.UserOfflineMessageQueryServiceImpl;

import java.util.concurrent.CountDownLatch;

/**
 * @author zzz
 * @Date 19/06/2023
 */
public class RpcDBAgent {
    static ApplicationConfig applicationConfig = new ApplicationConfig("dubbo-api-DBAgent-provider");
    static RegistryConfig registryConfig = new RegistryConfig("zookeeper://s1:2181");

    void publishAllServices() {
        publishQueryUserOfflineMessageRecordService();
        publishProvidesMessageStoreFilenameService();
    }

    public void publishQueryUserOfflineMessageRecordService() {
        //rpc serviceConfig provider
        ServiceConfig<IUserOfflineMessageQueryService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(IUserOfflineMessageQueryService.class);
        serviceConfig.setRef(new UserOfflineMessageQueryServiceImpl());

        serviceConfig.setApplication(applicationConfig);
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.export();

        System.out.println("dubbo service started......");
        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    void publishProvidesMessageStoreFilenameService() {
        ServiceConfig<UserOfflineMessageQueryServiceImpl> service = new ServiceConfig<>();
        service.setInterface(IUserOfflineMessageQueryService.class);
        service.setRef(new UserOfflineMessageQueryServiceImpl());

        service.setApplication(applicationConfig);
        service.setRegistry(registryConfig);
        service.export();

//        logger.info("dubbo service started......");

        System.out.println("dubbo service started......");
        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
