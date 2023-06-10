package org.messageServer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebSocketServer {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    // HTTP 协议解析，解码和编码，用于握手阶段
                    pipeline.addLast("http-codec", new HttpServerCodec());
                    //http请求聚合处理，多个HTTP请求或响应聚合为一个FullHtppRequest或FullHttpResponse
                    pipeline.addLast("aggregator",new HttpObjectAggregator(65536));
                    //大数据的分区传输
                    pipeline.addLast("http-chunked", new ChunkedWriteHandler());
                    // WebSocket 数据压缩扩展
                    pipeline.addLast("date-compression",new WebSocketServerCompressionHandler());
                    //鉴权处理器
                    pipeline.addLast("auth", new WebSocketAuthHandler());
                    // WebSocket 握手、控制帧处理
                    pipeline.addLast("websocket",new WebSocketServerProtocolHandler("/imServer",
                            null, true));
                    //消息转发处理器
                    pipeline.addLast("im",new messageForwardingWebSocketServerHandler());
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

