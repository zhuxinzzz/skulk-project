package org.messageServer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.SneakyThrows;
import org.messageServer.channelHandler.Auth;
import org.messageServer.channelHandler.MessageForwarding;


public class WebSocketServer2 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("IMServer start...");
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    // HTTP 协议解析，解码和编码，用于握手阶段
                    ch.pipeline().addLast("http-codec", new HttpServerCodec());
                    //http请求聚合处理，多个HTTP请求或响应聚合为一个FullHtppRequest或FullHttpResponse
                    ch.pipeline().addLast("aggregator", new HttpObjectAggregator(65536));
                    //大数据的分区传输
                    ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
                    // WebSocket 数据压缩扩展
                    ch.pipeline().addLast("date-compression", new WebSocketServerCompressionHandler());

                    //鉴权处理器
                    ch.pipeline().addLast("auth", new Auth());
                    // WebSocket 握手、控制帧处理
                    ch.pipeline().addLast("websocket", new WebSocketServerProtocolHandler("/imServer",
                            null, true));
                    //pipeline.addLast(new WebSocketServerHandler());

//                    ch.pipeline().addLast(new OfflineMessageProcessing());
                    //消息转发处理器
//                    ch.pipeline().addLast("im", new MyWebSocketServerHandler());
//                    ch.pipeline().addLast("im2", new MyWebSocketServerHandler2());
//                    ch.pipeline().addLast("im99", new messageForwardingWebSocketServerHandler());
//                    ch.pipeline().addLast( new WebSocketServerHandler());
                    ch.pipeline().addLast(new MessageForwarding());
                }
            });
            ChannelFuture channelFuture = serverBootstrap.bind(9999).sync();
            channelFuture.channel().closeFuture().sync();

        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }


}

