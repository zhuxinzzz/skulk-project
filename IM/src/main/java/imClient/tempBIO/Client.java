package imClient.tempBIO;

import java.io.*;
import java.net.Socket;

/*
* 2、设计一个基于socket的聊天程序：
    （1）、可以进行1v1单聊,
    （2）、如果好友不在线，可以发送离线消息，在好友上线后，好友可以看到离线消息
*
* */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 9999);
        //从控制台接收输入的消息
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //通过outputstream发送消息
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        //通过inputstream接收server返回的消息
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String msgSend = null;
        String[] strings = {"name:", "target:"};
        for (String string : strings) {
            System.out.println(string);
            msgSend = bufferedReader.readLine();
            bw.write(msgSend + "\n");//\n is important
            bw.flush();
        }
        showReceivedMsg(socket);
        while (!("exit".equals(msgSend))) {
            System.out.println("请输入信息：");
            msgSend = bufferedReader.readLine();
            bw.write(msgSend + "\n");
            bw.flush();
            System.out.println(reader.readLine());//print receive msg
        }
        socket.close();
    }

    public static void showReceivedMsg(Socket socket) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (!socket.isClosed()) {
                            System.out.println(br.readLine());//print receive msg
                        }else {
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
//    private Socket socket;
//    public Client() throws IOException {
//        socket = new Socket("localhost", 9999);
//    }
//    public void showReceivedMsg() throws IOException {
////        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
////        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
////        byte[] bytes = new byte[1024];
////        int len;
////        StringBuilder stringBuilder = new StringBuilder();
////        while ((len = inputStream.read(bytes)) != -1) {
////            stringBuilder.append(new String(bytes, 0, len,StandardCharsets.UTF_8));
////        }
////        System.out.printf("server:"+stringBuilder);
//        InputStream inputStream = this.socket.getInputStream();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                byte[] bytes = new byte[1024];
//                int len;
//                StringBuilder stringBuilder = new StringBuilder();
//                for (; ; ) {
//                    if (socket.isClosed()) {
//                        break;
//                    }
//                    try {
//                        while ((len = inputStream.read(bytes)) != -1) {
//                            stringBuilder.append(new String(bytes, 0, len, StandardCharsets.UTF_8));
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(stringBuilder);
//                }
//            }
//        }).start();
//    }
}
