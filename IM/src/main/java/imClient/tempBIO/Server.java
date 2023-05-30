package imClient.tempBIO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zzz
 */
public class Server {
    public static void main(String[] args) {
        Server serverZ = new Server();
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        for (; ; ) {
            try {
                Socket acceptSocket = serverZ.serverSocket.accept();
                executorService.submit(new ClientHandler(acceptSocket));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    ServerSocket serverSocket;
    //    <port,socket>
    static Map<Integer, Socket> userInfoMap = new HashMap();


    public Server() {
        try {
            serverSocket = new ServerSocket(9999);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class ClientHandler extends Thread {
    private final Socket socket;

    ClientHandler(Socket socket) {
        this.socket = socket;
    }

    //        show received msg.reply "server copied".if receive "exit",close socket.
    @Override
    public void run() {
        Map<Integer, Socket>     userInfoMap = Server.userInfoMap;
        int flag = 1;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msgSource = "";
            Socket socketOther = null;
            BufferedWriter bwOther = null;
            String nameSource=null;
            String nameTarget=null;
            while ((msgSource = reader.readLine()) != null) {
                switch (flag) {
                    case 1:
                        nameSource=msgSource;
                        addUserToMap(userInfoMap, nameSource, socket);//name,socket
                        System.out.println(nameSource + "connected.");
                        flag++;
                        break;
                    case 2:
                        nameTarget=msgSource;
                        socketOther = userInfoMap.get(nameTarget);
                        bwOther = new BufferedWriter(new OutputStreamWriter(socketOther.getOutputStream()));
                        System.out.println(nameTarget + "targeted");
                        flag++;
                        break;
                    case 3:
                        System.out.println(nameSource + ">>>\t" + nameTarget + "\t" + msgSource);
                        bwOther.write(msgSource + "\n");
                        bwOther.flush();
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int addUserToMap(Map map, String name, Socket socket) {
//        map.put()
        map.put(name, socket);
        return 0;
    }
}
