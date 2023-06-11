package org.l9.dbagent;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.config.utils.SimpleReferenceCache;
import org.game.skulk.DBAgent.IUserOfflineMessageQueryService;

/**
 * @author zzz
 * @Date 10/06/2023
 */
public class ManageOfflineMessages implements IUserOfflineMessageQueryService {
    @Override
    public String getUserOfflineMessage(String userName) {
        ReferenceConfig<IUserOfflineMessageQueryService> reference = new ReferenceConfig<>();
        reference.setInterface(IUserOfflineMessageQueryService.class);
        DubboBootstrap bootstrap = DubboBootstrap.getInstance();
        bootstrap.application(new ApplicationConfig("dubbo-api-Task-consumer"))
                .registry(new RegistryConfig("zookeeper://s1:2181"))
                .reference(reference).start();
        IUserOfflineMessageQueryService helloService = SimpleReferenceCache.getCache().get(reference);
        String message2 = helloService.getUserOfflineMessage(userName);
        System.out.println(message2);

        return message2;
    }

    @Override
    public String writeRedisUserOfflineMessageRecord(String userName, String record) {
        return null;
    }
}
