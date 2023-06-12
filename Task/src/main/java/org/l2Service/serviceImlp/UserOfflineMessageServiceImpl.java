package org.l2Service.serviceImlp;

import org.TaskServer;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.config.utils.SimpleReferenceCache;
import org.game.skulk.DBAgent.IUserOfflineMessageQueryService;
import org.game.skulk.Task.IUserOfflineMessageService;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author zzz
 * @Date 10/06/2023
 */
public class UserOfflineMessageServiceImpl implements IUserOfflineMessageService {
    @Override
    public String getUserOfflineMessage(String userName) {
        System.out.println("getUserOfflineMessage");
        /*consumer*/
        ReferenceConfig<IUserOfflineMessageQueryService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(TaskServer.applicationConfig);
        /*设置zk的地址和端口号。作为注册中心，存放服务提供端的地址和端口号。*/
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://s1:2181"));
        /*设置服务的接口，定义完成rpc调用的参数和返回值，统一规范。*/
        referenceConfig.setInterface(IUserOfflineMessageQueryService.class);
        /*获取代理动态生成的类对象（实例），调用接口对应的实现方法。完成rpc调用。*/
        IUserOfflineMessageQueryService service = referenceConfig.get();
        /*调用方法。获取返回值。保存到message变量里。*/
        String message = service.getUserOfflineMessageMessageRecord(userName);

        System.out.println(message);

        return message;
    }

    @Test
    public void test() {
        UserOfflineMessageServiceImpl userOfflineMessageServiceImpl = new UserOfflineMessageServiceImpl();
        String userOfflineMessageMessageRecord = userOfflineMessageServiceImpl.getUserOfflineMessageMessageRecord("user1");
        Assert.assertEquals("value1", userOfflineMessageMessageRecord);
    }

    @Override
    public String getUserOfflineMessageMessageRecord(String userName) {
        System.out.println("getUserOfflineMessageMessageRecord");
        ReferenceConfig<IUserOfflineMessageQueryService> reference = new ReferenceConfig<>();
        reference.setInterface(IUserOfflineMessageQueryService.class);
        DubboBootstrap bootstrap = DubboBootstrap.getInstance();
        bootstrap.application(new ApplicationConfig("dubbo-api-Task-consumer"))
                .registry(new RegistryConfig("zookeeper://s1:2181"))
                .reference(reference).start();
        IUserOfflineMessageQueryService service = SimpleReferenceCache.getCache().get(reference);

        String message2 = service.getUserOfflineMessageMessageRecord(userName);
        System.out.println(message2);
        return message2;

    }

    @Override
    public String writeRedisUserOfflineMessageRecord(String userName, String record) {
        return null;
    }
}
