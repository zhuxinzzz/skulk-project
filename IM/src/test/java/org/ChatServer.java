package org;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

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
