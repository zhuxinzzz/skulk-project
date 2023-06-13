package org.messageServer;
//import io.netty.handler.codec.http.websocketx.WebSocketUtil;
//import io.netty.handler.codec.http.websocketx.WebSocketUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import org.messageServer.SessionUtil;
import org.messageServer.messageForwardingWebSocketServerHandler;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class WebSocketAuthHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String welcomeMsg = "Welcome to my HTTP server!";
        System.out.println(welcomeMsg);
//        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
//                HttpResponseStatus.OK, Unpooled.wrappedBuffer(welcomeMsg.getBytes()));
//        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
//        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
//        ctx.writeAndFlush(response);

//        ctx.channel().writeAndFlush(new TextWebSocketFrame("aaa"));
//        super.channelActive(ctx);
    }

    //    private static final Gson GSON = new Gson();
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
    public void channelRead0(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
        String uri = req.uri();
        System.out.println(uri);

        String[] split = uri.split("/");
        String userName = split[2];
        String password = split[3];

        if (!hasLogin(userName)) {
            if (userMapOffline.get(userName) != null) {
                userMapOffline.remove(userName);
                userMapOnline.put(userName, password);

                SessionUtil.userMap.put(userName, ctx.channel());
                req.setUri("/" + split[1]);

                // 升级为 WebSocket 协议
//                WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
//                        "ws://" + req.headers().get("Host") + req.uri(), null, false);
//                WebSocketServerHandshaker handshaker = wsFactory.newHandshaker(req);
//                if (handshaker == null) {
//                    WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
//                } else {
//                    handshaker.handshake(ctx.channel(), req);
//                }



// 登录成功，返回升级为WebSocket的响应



                String secWebSocketKey = req.headers().get(HttpHeaderNames.SEC_WEBSOCKET_KEY);
//                String secWebSocketAccept = WebSocketUtil.getSecWebSocketAccept(secWebSocketKey);
                String secWebSocketAccept = calculateWebSocketAccept(secWebSocketKey);
//                req.headers().set(HttpHeaderNames.SEC_WEBSOCKET_ACCEPT, secWebSocketAccept);

                FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.SWITCHING_PROTOCOLS);
                response.headers().set(HttpHeaderNames.UPGRADE, HttpHeaderValues.WEBSOCKET);
                response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.UPGRADE);
                response.headers().set(HttpHeaderNames.SEC_WEBSOCKET_ACCEPT, secWebSocketAccept);
                ctx.writeAndFlush(response);
                // 修改ChannelPipeline，添加WebSocketChannelHandler
                ctx.pipeline().addLast(new WebSocketServerProtocolHandler("/websocket"));
                ctx.pipeline().addLast("im1",new messageForwardingWebSocketServerHandler());
                ctx.pipeline().remove(this);
//
//                ctx.pipeline().replace(this,
//                        "im1",
//                        new messageForwardingWebSocketServerHandler());


            } else {
                ctx.channel().writeAndFlush(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                        HttpResponseStatus.UNAUTHORIZED));
                ctx.channel().close();
            }

        }
/*fireChannelRead() 方法用于将数据传递给下一个处理器进行处理。
retain，增加了req实例的引用计数器，确保这个实例不会被释放。
* */
//        ctx.fireChannelRead(req.retain());
    }
    public static String calculateWebSocketAccept(String secWebSocketKey) throws NoSuchAlgorithmException {
        String magicString = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
        String concatenatedKey = secWebSocketKey + magicString;

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        messageDigest.update(concatenatedKey.getBytes(StandardCharsets.UTF_8));
        byte[] digest = messageDigest.digest();

        return Base64.getEncoder().encodeToString(digest);
    }

//    @Override
//    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
//        if (LoginUtil.hasLogin(ctx.channel())) {
//            System.out.println("当前用户已登录，无需再次校验");
//        } else {
//            System.out.println("未登录终止连接");
//        }
//    }
}