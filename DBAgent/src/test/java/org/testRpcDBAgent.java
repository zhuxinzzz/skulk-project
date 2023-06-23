package org;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.game.skulk.api.DBAgent.IMessageStoreService;
import org.game.skulk.api.DBAgent.IUserOfflineMessageQueryService;
import org.game.skulk.api.Task.IUserOfflineMessageService;
import org.junit.Test;
import org.serviceImpl.IMessageStoreServiceImpl;
import org.serviceImpl.UserOfflineMessageQueryServiceImpl;

import java.util.concurrent.CountDownLatch;

/**
 * @author zzz
 * @Date 20/06/2023
 */
public class testRpcDBAgent {
    static RpcDBAgent rpcDBAgent = new RpcDBAgent();
    ApplicationConfig applicationConfig = new ApplicationConfig("user-service");
    RegistryConfig registryConfig = new RegistryConfig("zookeeper://s1:2181");

    @Test
    public void testPublishAllRPCServices() {
        rpcDBAgent.publishAllServices();

    }

    @Test
    public void testPublishAllService2() {
        new Thread(() -> rpcDBAgent.publishQueryUserOfflineMessageRecordService()).start();
        new Thread(() -> rpcDBAgent.publishProvidesMessageStoreFilenameService()).start();

        System.out.println("dubbo service started......");
        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void testPublishAllService() {
        ServiceConfig<IUserOfflineMessageQueryService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(IUserOfflineMessageQueryService.class);
        serviceConfig.setRef(new UserOfflineMessageQueryServiceImpl());
        serviceConfig.setApplication(applicationConfig);
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.export();

        ServiceConfig<IMessageStoreService> service = new ServiceConfig<>();
        service.setInterface(IMessageStoreService.class);
        service.setRef(new IMessageStoreServiceImpl());
        service.setApplication(applicationConfig);
        service.setRegistry(registryConfig);
        service.export();

        System.out.println("dubbo service started......");
        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testPublishGetOfflineMessageRecordingService() {
        rpcDBAgent.publishQueryUserOfflineMessageRecordService();
    }

    @Test
    public void testReleaseGetFilenameService() {
        rpcDBAgent.publishProvidesMessageStoreFilenameService();
    }
}
