package org;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.game.skulk.DBAgent.IUserOfflineMessageQueryService;
import org.junit.Test;
import org.serviceImpl.UserOfflineMessageQueryServiceImpl;

import java.util.concurrent.CountDownLatch;

/**
 * @author zzz
 * @Date 09/06/2023
 */
public class DBAgentServer {
    public static void main(String[] args) {
        DBAgentServer dbAgentServer = new DBAgentServer();
        /**/
        dbAgentServer.publishQueryUserOfflineMessageRecordService();

    }

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
}
