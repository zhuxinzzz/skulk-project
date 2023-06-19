package org;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.game.skulk.api.DBAgent.IMessageStoreService;
import org.game.skulk.api.DBAgent.IUserOfflineMessageQueryService;
import org.junit.Test;
import org.serviceImpl.IMessageStoreServiceImpl;
import org.serviceImpl.UserOfflineMessageQueryServiceImpl;

import java.util.concurrent.CountDownLatch;

/**
 * @author zzz
 * @Date 19/06/2023
 */
public class RpcDBAgent {
    @Test
    public void test() {
        publishQueryUserOfflineMessageRecordService();
    }

    public void publishQueryUserOfflineMessageRecordService() {
        //rpc serviceConfig provider
        ServiceConfig<IUserOfflineMessageQueryService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(IUserOfflineMessageQueryService.class);
        serviceConfig.setRef(new UserOfflineMessageQueryServiceImpl());

        serviceConfig.setApplication(new ApplicationConfig("dubbo-api-DBAgent-provider"));
        serviceConfig.setRegistry(new RegistryConfig("zookeeper://s1:2181"));
        serviceConfig.export();

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test1() {
        publishProvidesMessageStoreFilenameService();

    }

    void publishProvidesMessageStoreFilenameService() {
        // TODO
        /*设置服务提供方，提供的方法实例的配置类*/
        ServiceConfig<IMessageStoreServiceImpl> service = new ServiceConfig<>();
        service.setRef(new IMessageStoreServiceImpl());
        service.setInterface(IMessageStoreService.class);

//		service.setId("dubbo-test");
        /*使用启动器*/
        DubboBootstrap bootstrap = DubboBootstrap.getInstance();
        bootstrap.application(new ApplicationConfig("dubbo-api-db-agent-FN-provider"))
                .registry(new RegistryConfig("zookeeper://s1:2181")).service(service).start();
        /*等待响应*/
        bootstrap.await();
    }
}
