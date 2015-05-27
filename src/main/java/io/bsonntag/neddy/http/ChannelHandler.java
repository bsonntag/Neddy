
package io.bsonntag.neddy.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


/**
 * ChannelHandler
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 22/mai/2015
 */
final class ChannelHandler extends SimpleChannelInboundHandler<HttpRequest> {
    
    private final HttpEventBus eventBus;

    ChannelHandler(HttpEventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, HttpRequest request) {
        HttpResponse response = new HttpResponse();
        eventBus.emitRequestEvent(request, response);
        sendResponse(ctx, response);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable exception) {
        HttpResponse response = new HttpResponse();
        eventBus.emitServerErrorEvent(exception, response);
        sendResponse(ctx, response);
    }

    private void sendResponse(ChannelHandlerContext ctx, HttpResponse response) {
        ctx.writeAndFlush(response);
        ctx.close();
    }
    
}
