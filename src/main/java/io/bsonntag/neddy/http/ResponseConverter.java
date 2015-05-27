
package io.bsonntag.neddy.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

/**
 * ResponseConverter
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 23/mai/2015
 */
class ResponseConverter {
    
    static io.netty.handler.codec.http.HttpResponse convert(HttpResponse response) {
        return new ResponseConverter(response).convert();
    }
    
    
    private final HttpResponse response;
    private io.netty.handler.codec.http.HttpResponse nettyResponse;

    private ResponseConverter(HttpResponse response) {
        this.response = response;
    }
    
    private io.netty.handler.codec.http.HttpResponse convert() {
        nettyResponse = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                convertStatus(),
                convertContent());
        
        convertHeader();
        
        return nettyResponse;
    }
    
    private HttpResponseStatus convertStatus() {
        return HttpResponseStatus.valueOf(response.getStatus());
    }
    
    private ByteBuf convertContent() {
        return Unpooled.copiedBuffer(response.getContent(), CharsetUtil.UTF_8);
    }
    
    private void convertHeader() {
        response.getHeader().forEach(entry -> {
            nettyResponse.headers().add(entry.getName(), entry.getValues());
        });
    }
    
}
