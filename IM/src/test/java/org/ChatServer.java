package org;

import org.IMServer;
import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketAdapter;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.Handshakedata;
import org.java_websocket.handshake.ServerHandshake;
import org.junit.Test;
import org.messageServer.WebSocketServer;

import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author zzz
 * @Date 12/06/2023
 */
public class ChatServer {
    @Test
    public void test1() {
        System.out.println(new Date());
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("EEE-MMM-dd-HH:mm:ss-zzz-yyyy");
        String dateString = format.format(date);
//        dateString = dateString.replace(" ", "-");

        System.out.println(dateString);
    }

    @Test
    public void test() {
        IMServer imServer = new IMServer();
        imServer.startTheMessageForwardingServer();

    }

//    @Test
//    public void testCli() {
//        URI uri = null;
//        try {
//            uri = new URI("ws://127.0.0.1:9999/imServer/user1/123");
//        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
//        }
//        MyWebSocketClient client = new MyWebSocketClient(uri);
//        client.connect();
//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            String input = scanner.nextLine();
//            if ("quit".equalsIgnoreCase(input)) {
//                client.close();
//                break;
//            } else {
//                client.send(input);
//            }
//        }
//    }

}
