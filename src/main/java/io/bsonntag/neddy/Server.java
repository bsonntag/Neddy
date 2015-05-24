
package io.bsonntag.neddy;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.bootstrap.ServerChannelFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;

/**
 * Server
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 22/mai/2015
 */
public final class Server {
    
    private final EventLoopGroup bossGroup;
    private final EventLoopGroup workerGroup;
    private final ServerBootstrap serverBootstrap;

    public Server(EventLoopGroup bossGroup,
            EventLoopGroup workerGroup,
            ServerChannelFactory<ServerSocketChannel> channelFactory,
            ChannelInitializer channelInitializer) {
        this.bossGroup = bossGroup;
        this.workerGroup = workerGroup;
        
        serverBootstrap = new ServerBootstrap()
                .group(bossGroup, workerGroup)
                .channelFactory(channelFactory)
                .childHandler(channelInitializer);
    }
    
    public void listen(int port) {
        ChannelFuture bindFuture = serverBootstrap.bind(port);
        System.out.println("Server running on port " + port);
        afterBind(bindFuture);
    }
    
    public void listen(int port, String host) {
        ChannelFuture bindFuture = serverBootstrap.bind(host, port);
        System.out.println("Server running on port " + port);
        afterBind(bindFuture);
    }
    
    private void afterBind(ChannelFuture bindFuture) {
        try {
            waitForShutdown(bindFuture.channel());
        }
        catch(InterruptedException ex) {
            ex.printStackTrace();
        }
        finally {
            finishShutdown();
        }
    }
    
    private void waitForShutdown(Channel channel) throws InterruptedException {
        channel.closeFuture().sync();
    }

    private void finishShutdown() {
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }
    
}
