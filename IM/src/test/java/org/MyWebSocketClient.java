package org;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class MyWebSocketClient extends WebSocketClient {

    public MyWebSocketClient(URI uri) {
        super(uri);
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        System.out.println("WebSocket opened: " + handshake.getHttpStatus() + " " + handshake.getHttpStatusMessage());
    }

    @Override
    public void onMessage(String message) {
        System.out.println("Received message: " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("WebSocket closed: " + code + " " + reason);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    public static void main(String[] args) throws URISyntaxException {
        URI uri = new URI("ws://localhost:8080/websocket");
        MyWebSocketClient client = new MyWebSocketClient(uri);
        client.connect();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if ("quit".equalsIgnoreCase(input)) {
                client.close();
                break;
            } else {
                client.send(input);
            }
        }
    }
}