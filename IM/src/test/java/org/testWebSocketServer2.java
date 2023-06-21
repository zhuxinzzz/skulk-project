package org;

import lombok.SneakyThrows;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;

/**
 * @author zzz
 * @Date 20/06/2023
 */
public class testWebSocketServer2 {
    @Test
    public void testServe2r() throws InterruptedException {
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Class<?> cls = null;
                cls = Class.forName("org.messageServer.WebSocketServer2");
                Method mainMethod = cls.getDeclaredMethod("main", String[].class);
                mainMethod.invoke(null, (Object) new String[0]);
            }
        }).start();
        new CountDownLatch(1).await();
    }

    @Test
    public void testServer() throws InterruptedException {
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Class<?> cls = null;
                cls = Class.forName("org.messageServer.WebSocketServer");
                Method mainMethod = cls.getDeclaredMethod("main", String[].class);
                mainMethod.invoke(null, (Object) new String[0]);
            }
        }).start();
        new CountDownLatch(1).await();
    }

}
