package org.dao;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.game.skulk.api.DBAgent.IUserOfflineMessageQueryService;
import org.game.skulk.api.Task.IUserOfflineMessageService;
import org.junit.Test;

// 创建一个服务实现类
class UserServiceImpl implements IUserOfflineMessageQueryService {
//    @Override
//    public String sayHello(String name) {
//        return "Hello, " + name;
//    }

    @Override
    public String getUserOfflineMessageMessageRecord(String userName) {
        return null;
    }

    @Override
    public String writeRedisUserOfflineMessageRecord(String userName, String record) {
        return null;
    }
}

// 创建另一个服务实现类
class OrderServiceImpl implements IUserOfflineMessageService {
//    @Override
//    public String getOrderInfo(String orderId) {
//        return "Order Info: " + orderId;
//    }

    @Override
    public String getUserOfflineMessage(String userName) {
        return null;
    }

    @Override
    public String getUserOfflineMessageMessageRecord(String userName) {
        return null;
    }

    @Override
    public String writeRedisUserOfflineMessageRecord(String userName, String record) {
        return null;
    }
}


// 创建一个发布多个服务的示例
public class MultiServiceExample {
    @Test
//    public static void main(String[] args) {
    public void main() {
        ApplicationConfig applicationConfig = new ApplicationConfig("user-service");

        // 创建一个 Dubbo 服务配置对象
        ServiceConfig<IUserOfflineMessageQueryService> userServiceConfig = new ServiceConfig<>();
        // 设置服务接口和实现类
        userServiceConfig.setInterface(IUserOfflineMessageQueryService.class);
        userServiceConfig.setRef(new UserServiceImpl());
        // 设置服务名称
        userServiceConfig.setApplication(applicationConfig);
        userServiceConfig.setRegistry(new RegistryConfig("zookeeper://s1:2181"));
        userServiceConfig.export();

        // 创建另一个 Dubbo 服务配置对象
        ServiceConfig<IUserOfflineMessageService> orderServiceConfig = new ServiceConfig<>();
        // 设置服务接口和实现类
        orderServiceConfig.setInterface(IUserOfflineMessageService.class);
        orderServiceConfig.setRef(new OrderServiceImpl());
        // 设置服务名称
//        orderServiceConfig.setApplication(new ApplicationConfig("order-service"));
        orderServiceConfig.setApplication(applicationConfig);
        orderServiceConfig.setRegistry(new RegistryConfig("zookeeper://s1:2181"));
        orderServiceConfig.export();


    }
}