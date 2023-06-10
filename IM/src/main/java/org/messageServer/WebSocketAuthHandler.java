package org.messageServer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.HashMap;
import java.util.Map;

public class WebSocketAuthHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().writeAndFlush(new TextWebSocketFrame("aaa"));
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
        return userMapOnline.get(userName)!=null;
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
                req.setUri("/"+split[1]);
            }else {
                ctx.channel().writeAndFlush(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                        HttpResponseStatus.UNAUTHORIZED));
                ctx.channel().close();
            }

        }

/*fireChannelRead() 方法用于将数据传递给下一个处理器进行处理。
retain，增加了req实例的引用计数器，确保这个实例不会被释放。
* */
        ctx.fireChannelRead(req.retain());
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