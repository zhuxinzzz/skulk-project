package org;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.config.utils.SimpleReferenceCache;
import org.game.skulk.api.Task.IUserOfflineMessageService;
import org.messageServer.WebSocketServer2;

import java.lang.reflect.Method;

/**
 * @author zzz
 * @Date 09/06/2023
 */
public class IMServer {
    public static void main(String[] args) {
        IMServer imServer = new IMServer();
        /*启动消息转发服务器*/
        imServer.startTheMessageForwardingServer();
        /*获取用户离线消息*/
//        imServer.getUserOfflineMessageRecords("user1");
        /*生产写入聊天文件消息*/
//        imServer.productionWritesChatFileMessages("file1.bak", "line1line2");
    }
    public void startTheMessageForwardingServer() {
        new Thread(() -> {
            Class<?> cls = null;
            try {
                cls = Class.forName("org.messageServer.WebSocketServer2");
                Method mainMethod = cls.getDeclaredMethod("main", String[].class);
                mainMethod.invoke(null, (Object) new String[0]);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).run();
    }
}
