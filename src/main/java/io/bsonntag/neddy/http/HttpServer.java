
package io.bsonntag.neddy.http;

import io.bsonntag.neddy.events.BiEventListener;
import io.bsonntag.neddy.net.Server;
import io.netty.bootstrap.ServerChannelFactory;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import java.util.function.Supplier;

/**
 * HttpServer
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 22/mai/2015
 */
public final class HttpServer extends Server<HttpRequest, HttpResponse> {
    
    private final HttpEventBus eventBus;

    HttpServer(Supplier<EventLoopGroup> eventLoopFactory,
            ServerChannelFactory<ServerSocketChannel> channelFactory,
            ChannelInitializer channelInitializer,
            HttpEventBus eventBus) {
        super(eventLoopFactory, channelFactory, channelInitializer);
        this.eventBus = eventBus;
    }
    
    @Override
    public HttpServer onRequest(BiEventListener<HttpRequest, HttpResponse> listener) {
        eventBus.onRequest(listener);
        return this;
    }
    
    @Override
    public HttpServer onServerError(BiEventListener<Throwable, HttpResponse> listener) {
        eventBus.onServerError(listener);
        return this;
    }
    
}
