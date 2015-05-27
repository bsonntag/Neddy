
package io.bsonntag.neddy.http;

import io.bsonntag.neddy.http.events.DefaultServerErrorListener;
import io.bsonntag.neddy.http.events.HttpRequestListener;
import io.netty.bootstrap.ServerChannelFactory;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import java.util.function.Supplier;

/**
 * Http
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 22/mai/2015
 */
public final class Http {

    private Http() { }
    
    private static final Supplier<EventLoopGroup> eventLoopFactory =
            NioEventLoopGroup::new;
    private static final ServerChannelFactory<ServerSocketChannel> channelFactory =
            (eventLoop, childGroup) -> new NioServerSocketChannel(eventLoop, childGroup);
    
    public static HttpServer createServer(HttpRequestListener requestListener) {
        HttpEventBus eventBus = new HttpEventBus();
        HttpServer server = new HttpServer(
                eventLoopFactory,
                channelFactory,
                new HttpInitializer(eventBus),
                eventBus);
        return server.onRequest(requestListener)
                .onServerError(DefaultServerErrorListener.get());
    }
    
    private static class HttpInitializer extends ChannelInitializer<SocketChannel> {
        
        private final HttpEventBus eventBus;

        private HttpInitializer(HttpEventBus eventBus) {
            this.eventBus = eventBus;
        }
        
        @Override
        protected void initChannel(SocketChannel channel) throws Exception {
            channel.pipeline()
                    .addLast("requestDecoder", new HttpRequestDecoder())
                    .addLast("requestTransformer", new RequestTransformer())
                    .addLast("responseEncoder", new HttpResponseEncoder())
                    .addLast("responseTransformer", new ResponseTransformer())
                    .addLast("handler", new ChannelHandler(eventBus));
        }
        
    }
    
}
