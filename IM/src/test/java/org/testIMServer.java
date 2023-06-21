package org;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author zzz
 * @Date 20/06/2023
 */
public class testIMServer {
    @Test
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
