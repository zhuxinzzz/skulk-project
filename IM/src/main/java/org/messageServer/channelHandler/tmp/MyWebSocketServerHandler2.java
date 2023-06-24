package org.messageServer.channelHandler.tmp;

import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;
import org.messageServer.pojo.ClientMessage;
import org.messageServer.util.SessionUtil;

import java.util.HashMap;
import java.util.Map;

import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * 消息转发
 * 消息存储
 */
public class MyWebSocketServerHandler2 extends SimpleChannelInboundHandler<Object> {
    private static final Gson GSON = new Gson();
    private WebSocketServerHandshaker handshaker;
    private static final Map<String, String> userMapOffline = new HashMap<>();
    private static final Map<String, String> userMapOnline = new HashMap<>();

    static {
        userMapOffline.put("user1", "123");
        userMapOffline.put("user2", "123");
        userMapOffline.put("user3", "123");
        userMapOffline.put("user4", "123");
        userMapOffline.put("abc", "123");
        userMapOffline.put("qwe", "456");
        userMapOffline.put("zzz", "123");
    }

    private static boolean hasLogin(String userName) {
        return userMapOnline.get(userName) != null;
//        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);
//        return loginAttr.get() != null;
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel 2");

//        System.out.println("Client connected: " + ctx.channel().remoteAddress());
        //add user info to map

//        ctx.channel().writeAndFlush(new TextWebSocketFrame("Welcome to my WebSocket server!"));
//        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

        String requestText = ((TextWebSocketFrame) msg).text();
        ClientMessage clientMessage = GSON.fromJson(requestText, ClientMessage.class);
        String date = clientMessage.getDate();
        String toUserId = clientMessage.getToUserId();
        String fromUserId = clientMessage.getFromUserId();
        String messageMsg = clientMessage.getContent();

        Channel toChannel = SessionUtil.getChannel(toUserId);
        if (toChannel == null) {
            ctx.channel().writeAndFlush(new TextWebSocketFrame(
                    GSON.toJson(new ClientMessage(date, toUserId, fromUserId, "对方未在线" + messageMsg))));
            //Task，存储离线消息
//                String storedMessage = clientMessage.toString();
//                IMServer.productionWritesChatFileMessages("file1.bak", storedMessage);
            //mq


            //
        } else {
            toChannel.writeAndFlush(new TextWebSocketFrame(requestText));
        }

        // 如果是HTTP请求，进行握手操作
/*        if (messageMsg instanceof FullHttpRequest) {
            handleHttpRequest(ctx, (FullHttpRequest) messageMsg);
        }
        // 如果是WebSocket请求，则进行WebSocket处理
        else*/
//        if (messageMsg instanceof WebSocketFrame) {
//        handleWebSocketFrame(ctx, (WebSocketFrame) messageMsg);
//        }
    }

    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
        // 如果HTTP解码失败，则返回HTTP异常
        if (!req.decoderResult().isSuccess()) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_1, BAD_REQUEST));
            return;
        }

        // 构造握手响应返回，本机测试
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                "ws://localhost:9999/websocket", null, false);
        handshaker = wsFactory.newHandshaker(req);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), req);
        }
    }

    private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
//        // 判断是否是关闭链路的指令
//        if (frame instanceof CloseWebSocketFrame) {
//            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
//            return;
//        }
//
//        // 判断是否是Ping消息
//        if (frame instanceof PingWebSocketFrame) {
//            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
//            return;
//        }
//
//        // 本例程仅支持文本消息，不支持二进制消息
//        if (!(frame instanceof TextWebSocketFrame)) {
//            throw new UnsupportedOperationException(String.format("%s frame types not supported", frame.getClass().getName()));
//        }
        // 此处仅处理 Text Frame
        if (frame instanceof TextWebSocketFrame) {
            String requestText = ((TextWebSocketFrame) frame).text();
            ClientMessage clientMessage = GSON.fromJson(requestText, ClientMessage.class);
            String date = clientMessage.getDate();
            String toUserId = clientMessage.getToUserId();
            String fromUserId = clientMessage.getFromUserId();
            String msg = clientMessage.getContent();

            Channel toChannel = SessionUtil.getChannel(toUserId);
            if (toChannel == null) {
                ctx.channel().writeAndFlush(new TextWebSocketFrame(
                        GSON.toJson(new ClientMessage(date, toUserId, fromUserId, "对方未在线" + msg))));
                //Task，存储离线消息
//                String storedMessage = clientMessage.toString();
//                IMServer.productionWritesChatFileMessages("file1.bak", storedMessage);
                //mq


                //
            } else {
                toChannel.writeAndFlush(new TextWebSocketFrame(requestText));
            }
        }


//        // 处理来自客户端的请求
//        String request = ((TextWebSocketFrame) frame).text();
//        System.out.println(String.format("%s received %s", ctx.channel(), request));
//        ctx.channel().write(new TextWebSocketFrame(request + " , 欢迎使用Netty WebSocket服务，现在时刻：" + new Date().toString()));
    }

    private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, FullHttpResponse res) {
        // 返回应答给客户端
        if (res.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
            HttpHeaders.setContentLength(res, res.content().readableBytes());
        }

        // 如果是非Keep-Alive，关闭连接
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if (!HttpHeaders.isKeepAlive(req) || res.status().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }
}