package org.messageServer;

import com.google.gson.Gson;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.IMServer;


public class messageForwardingWebSocketServerHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
    private static final Gson GSON = new Gson();
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("Client connected: " + ctx.channel().remoteAddress());
        String welcomeMsg = "Welcome to my WebSocket server!";
        ctx.channel().writeAndFlush(new TextWebSocketFrame(welcomeMsg));

//        System.out.println("Client connected: " + ctx.channel().remoteAddress());
//
//        String welcomeMsg = "Welcome to my WebSocket server!";
//        System.out.println(welcomeMsg);
//        TextWebSocketFrame welcomeMsgFrame = new TextWebSocketFrame(welcomeMsg);
//        ctx.writeAndFlush(welcomeMsgFrame);


//        // 新客户端连接，发送欢迎消息
//        ctx.channel().writeAndFlush(new TextWebSocketFrame("Welcome to the chat !"));
//        ctx.channel().writeAndFlush(new TextWebSocketFrame("aaa"));
    }

    /*连接建立之后，检查有无离线消息*/
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception {
        if (frame instanceof TextWebSocketFrame) { // 此处仅处理 Text Frame
            String requestText = ((TextWebSocketFrame) frame).text();
            ClientMessage clientMessage = GSON.fromJson(requestText, ClientMessage.class);
            String date = clientMessage.getDate();
            String toUserId = clientMessage.getToUserId();
            String fromUserId = clientMessage.getFromUserId();
            String msg = clientMessage.getMsg();

            Channel toChannel = SessionUtil.getChannel(clientMessage.getToUserId());
            if (toChannel == null) {
                ctx.channel().writeAndFlush(new TextWebSocketFrame(
                        GSON.toJson(new ClientMessage(date,toUserId, fromUserId, "对方未在线" + msg))));
                //Task，存储离线消息
                String storedMessage = clientMessage.toString();
                IMServer.productionWritesChatFileMessages("file1.bak", storedMessage);

                //mq


                //
            } else {
                toChannel.writeAndFlush(new TextWebSocketFrame(requestText));
            }
        }

    }

    public void handleOfflineMessages() {
//        检查,如果有离线消息,就从Task服务获取离线消息字符串
//        (Task从DBAgent的redis获取离线元数据,再从fs读文件的指定行数,)
//        Task返回离线消息,
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 新客户端连接，发送欢迎消息
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