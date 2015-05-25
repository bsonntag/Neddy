
package io.bsonntag.neddy.http;

import io.netty.bootstrap.ServerChannelFactory;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * Http
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 22/mai/2015
 */
public final class Http {

    private Http() { }
    
    private static final ServerChannelFactory<ServerSocketChannel> channelFactory =
            (eventLoop, childGroup) -> new NioServerSocketChannel(eventLoop, childGroup);
    
    public static HttpServer createServer(HttpRequestListener requestListener) {
        return new HttpServer(
                new NioEventLoopGroup(),
                new NioEventLoopGroup(),
                channelFactory,
                new HttpInitializer(requestListener));
    }
    
    private static class HttpInitializer extends ChannelInitializer<SocketChannel> {
        
        private final HttpRequestListener requestListener;

        private HttpInitializer(HttpRequestListener requestListener) {
            this.requestListener = requestListener;
        }
        
        @Override
        protected void initChannel(SocketChannel channel) throws Exception {
            channel.pipeline()
                    .addLast("decoder", new HttpRequestDecoder())
                    .addLast("encoder", new HttpResponseEncoder())
                    .addLast("handler", new ChannelHandler(requestListener));
        }
        
    }
    
}
