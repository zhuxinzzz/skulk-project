package org;

import org.apache.dubbo.config.*;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.config.utils.SimpleReferenceCache;
import org.game.skulk.api.DBAgent.IMessageStoreService;
import org.game.skulk.api.Task.IUserOfflineMessageService;
import org.junit.Assert;
import org.junit.Test;
import org.l2Service.serviceImlp.UserOfflineMessageServiceImpl;

import java.util.concurrent.CountDownLatch;


/**
 * @author zzz
 * @Date 19/06/2023
 */
public class RpcTask {
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
        RpcTask rpcTask = new RpcTask();
        rpcTask.publishAServiceForGettingOfflineMessages();
    }

    /*publish a service for getting offline messages*/
    void publishAServiceForGettingOfflineMessages() {
        new Thread(() -> publishAServiceToGetOfflineMessages1()).run();
    }

    private void publishAServiceToGetOfflineMessages1() {
        //rpc service provider
        protocolConfig.setPort(20890);
        /*服务配置*/
        ServiceConfig<IUserOfflineMessageService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(IUserOfflineMessageService.class);
        serviceConfig.setRef(new UserOfflineMessageServiceImpl());
        serviceConfig.setApplication(applicationConfig);
        serviceConfig.setRegistry(registryConfig);

        serviceConfig.setProtocol(protocolConfig);
        serviceConfig.export();

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void publishCheckOfflineMessageFunction() {
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

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void test() {
        String fileName = getUserOfflineMessageStorageFile("user1");
        System.out.println(fileName);

        Assert.assertEquals("user1|user2.bak", fileName);
    }
    /*getUserStorageInformationFile*/
    public String getUserOfflineMessageStorageFile(String userName) {
        ReferenceConfig<IMessageStoreService> reference = new ReferenceConfig<>();
        reference.setInterface(IMessageStoreService.class);

        DubboBootstrap bootstrap = DubboBootstrap.getInstance();
        bootstrap.application(new ApplicationConfig("dubbo-api-dbAgent-getFN-consumer"))
                .registry(new RegistryConfig("zookeeper://s1:2181")).reference(reference).start();
        IMessageStoreService service = SimpleReferenceCache.getCache().get(reference);

        String fileName = service.getUserOfflineMessageStorageFile(userName);

        return fileName;

    }


}
