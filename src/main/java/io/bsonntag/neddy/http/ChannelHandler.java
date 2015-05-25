
package io.bsonntag.neddy.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;

import static io.bsonntag.neddy.http.HttpHeaderField.contentType;

/**
 * ChannelHandler
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 22/mai/2015
 */
final class ChannelHandler extends SimpleChannelInboundHandler<HttpRequest> {
    
    private final RequestHandler handler;

    ChannelHandler(HttpRequestListener requestListener) {
        this.handler = new RequestHandler(requestListener);
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, HttpRequest request) {
        HttpResponse response = handler.handle(request);
        ctx.writeAndFlush(response);
        ctx.close();
    }
    
    
    private static class RequestHandler {

        private final HttpRequestListener requestListener;
        private io.bsonntag.neddy.http.HttpRequest request;
        private io.bsonntag.neddy.http.HttpResponse response;

        RequestHandler(HttpRequestListener requestListener) {
            this.requestListener = requestListener;
        }
        
        public io.netty.handler.codec.http.HttpResponse
                    handle(io.netty.handler.codec.http.HttpRequest nettyRequest) {
            request = RequestConverter.convert(nettyRequest);
            response = new io.bsonntag.neddy.http.HttpResponse();

            try {
                requestListener.handle(request, response);
            }
            catch(Exception ex) {
                response = new io.bsonntag.neddy.http.HttpResponse();
                response.writeHead(500, new HttpHeader(contentType("text/plain")));
                response.write(ex.toString());
            }

            return ResponseConverter.convert(response);
        }

    }
    
}
