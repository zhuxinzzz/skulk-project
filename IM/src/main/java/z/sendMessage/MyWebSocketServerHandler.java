package z.sendMessage;

import com.google.gson.Gson;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;


class MyWebSocketServerHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
    private static final Gson GSON = new Gson();
    
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        // 新客户端连接，发送欢迎消息
//        ctx.writeAndFlush(new TextWebSocketFrame("Welcome to the chat room!"));
//    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception {
        if (frame instanceof TextWebSocketFrame) { // 此处仅处理 Text Frame
            String requestText = ((TextWebSocketFrame) frame).text();
            ClientMessage clientMessage = GSON.fromJson(requestText, ClientMessage.class);

            Channel toChannel = SessionUtil.getChannel(clientMessage.getToUserId());
            toChannel.writeAndFlush(new TextWebSocketFrame(requestText));
//            ctx.channel().writeAndFlush(new TextWebSocketFrame(/*"收到: " +*/ requestText));
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 异常处理
        cause.printStackTrace();
        ctx.close();
    }
}