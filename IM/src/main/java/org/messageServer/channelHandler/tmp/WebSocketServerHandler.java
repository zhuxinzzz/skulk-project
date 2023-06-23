package org.messageServer.channelHandler.tmp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

public class WebSocketServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client connected: " + ctx.channel().remoteAddress());
        String welcomeMsg = "Welcome to my WebSocket server!";
        ctx.channel().writeAndFlush(new TextWebSocketFrame(welcomeMsg));
    }

//    @Override
//    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception {
//        // handle WebSocket frame
//    }

    // other methods...
}