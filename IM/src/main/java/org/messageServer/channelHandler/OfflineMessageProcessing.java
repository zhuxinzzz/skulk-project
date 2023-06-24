package org.messageServer.channelHandler;

import com.google.gson.Gson;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.util.AttributeKey;
import org.messageServer.pojo.ClientMessage;
import org.messageServer.util.SessionUtil;

public class OfflineMessageProcessing extends SimpleChannelInboundHandler<WebSocketFrame> {
    private static final Gson GSON = new Gson();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.print("message forwarding.\t");
        System.out.println("Client connected: " + ctx.channel().remoteAddress());
//        String welcomeMsg = "Welcome to my WebSocket server!";
//        System.out.println(welcomeMsg);
//        ctx.writeAndFlush(new TextWebSocketFrame(welcomeMsg));

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception {
        System.out.print("offline message\t");

        String offlineMessage = (String) ctx.attr(AttributeKey.valueOf("offlineMessage")).get();
        System.out.println(offlineMessage);
        if (!offlineMessage.equals("nullMessage")) {
            ctx.channel().writeAndFlush(new TextWebSocketFrame(offlineMessage));
        }
        ctx.pipeline().remove(this);

//        if (frame instanceof TextWebSocketFrame) { // 此处仅处理 Text Frame
//            String requestText = ((TextWebSocketFrame) frame).text();
//
//            ClientMessage clientMessage = GSON.fromJson(requestText, ClientMessage.class);
//            String date = clientMessage.getDate();
//            String toUserId = clientMessage.getToUserId();
//            String fromUserId = clientMessage.getFromUserId();
//            String msg = clientMessage.getMsg();
//
//            Channel toChannel = SessionUtil.getChannel(clientMessage.getToUserId());
//            // 获取 ChannelHandlerContext 中的键值对
//            String offlineMessage = (String) ctx.attr(AttributeKey.valueOf("offlineMessage")).get();
//            System.out.println(offlineMessage);
//            ctx.channel().writeAndFlush(new TextWebSocketFrame(offlineMessage));
//
//            if (toChannel == null) {
//                ClientMessage clientMessage1 = new ClientMessage(
//                        date, toUserId, fromUserId, "对方未在线" + msg);
//                TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(GSON.toJson(clientMessage1));
//                ctx.channel().writeAndFlush(textWebSocketFrame);
//                //Task，存储离线消息
//                //the file name is obtained from redis
//                //key is the messageStore-UserId,value is the file name.
//
//
////                IMServer.productionWritesChatFileMessages("file1.bak", storedMessage);
//                //mq
//
//                //
//            } else {
//                toChannel.writeAndFlush(new TextWebSocketFrame(requestText));
//            }
//        }
    }

    public void handleOfflineMessages() {
//        检查,如果有离线消息,就从Task服务获取离线消息字符串
//        (Task从DBAgent的redis获取离线元数据,再从fs读文件的指定行数,)
//        Task返回离线消息,
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 新客户端连接，发送欢迎消息
        System.out.println("InActive");
        ctx.channel().writeAndFlush(new TextWebSocketFrame("Welcome to the chat !"));
//        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 异常处理
        cause.printStackTrace();
        ctx.close();
    }
}