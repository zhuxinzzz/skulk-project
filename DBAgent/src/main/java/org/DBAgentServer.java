package org;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.game.skulk.DBAgent.IUserOfflineMessageQueryService;
import org.serviceImpl.L1Api;

import java.util.concurrent.CountDownLatch;

/**
 * @author zzz
 * @Date 09/06/2023
 */
public class DBAgentServer {
    public static void main(String[] args) throws InterruptedException {
        //rpc service provider
        /*设置服务提供方，提供的方法实例的配置类*/
        ServiceConfig<IUserOfflineMessageQueryService> service = new ServiceConfig<>();
        service.setInterface(IUserOfflineMessageQueryService.class);
        service.setRef(new L1Api());


        service.setApplication(new ApplicationConfig("dubbo-api-DBAgent-provider"));
        service.setRegistry(new RegistryConfig("zookeeper://s1:2181"));
        service.export();

        new CountDownLatch(1).await();
    }
}
