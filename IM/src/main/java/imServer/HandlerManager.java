package imServer;

import imServer.handler.WebSocketAuthHandler;
import imServer.handler.WsChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author zzz
 * @Date 30/05/2023
 */
public class HandlerManager extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
//http 协议编解码器
        ch.pipeline().addLast("http-codec", new HttpServerCodec());
        //http请求聚合处理，多个HTTP请求或响应聚合为一个FullHtppRequest或FullHttpResponse
        ch.pipeline().addLast("aggregator", new HttpObjectAggregator(65536));
        //鉴权处理器,aaa
        ch.pipeline().addLast("auth", new WebSocketAuthHandler());
        //大数据的分区传输
        ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
        //websocket协议处理器
        ch.pipeline().addLast("websocket", new WebSocketServerProtocolHandler("/imServer"));
        //自定义消息处理器aaa
        ch.pipeline().addLast("my-handler", new WsChannelHandler());
    }
}