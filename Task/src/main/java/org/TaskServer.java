package org;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.config.utils.SimpleReferenceCache;
import org.game.skulk.DBAgent.IUserOfflineMessageQueryService;

/**
 * @author zzz
 * @Date 09/06/2023
 */
public class TaskServer {
    public static void main(String[] args) {
        ReferenceConfig<IUserOfflineMessageQueryService> reference = new ReferenceConfig<>();
        reference.setInterface(IUserOfflineMessageQueryService.class);

        DubboBootstrap bootstrap = DubboBootstrap.getInstance();
        bootstrap.application(new ApplicationConfig("dubbo-api-Task-consumer"))
                .registry(new RegistryConfig("zookeeper://s1:2181"))
                .reference(reference).start();
        IUserOfflineMessageQueryService helloService = SimpleReferenceCache.getCache().get(reference);

//        String message = helloService.writeRedisUserOfflineMessageRecord("key1112","value2");
        String message2 = helloService.getUserOfflineMessage("key1112");

//        System.out.println(message);
        System.out.println(message2);
    }
}
