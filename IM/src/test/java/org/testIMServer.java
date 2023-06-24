package org;

import com.google.gson.Gson;
import org.junit.Test;
import org.messageServer.pojo.ClientMessage;

import java.lang.reflect.Method;

/**
 * @author zzz
 * @Date 20/06/2023
 */
public class testIMServer {
    private static final Gson GSON = new Gson();

    @Test
    public void test() {
        String requestText = "{\n" +
                "    \"date\":\"Tue-Jun-13-10:56:36-CST-2023\",\n" +
                "    \"toUserId\": \"user2\",\n" +
                "    \"fromUserId\": \"user1\",\n" +
                "    \"content\": \"new line msg1\"\n" +
                "}";
        ClientMessage clientMessage = GSON.fromJson(requestText, ClientMessage.class);
        System.out.println(clientMessage.toString());
    }

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
