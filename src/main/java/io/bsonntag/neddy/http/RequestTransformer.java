
package io.bsonntag.neddy.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.util.List;

/**
 * RequestTransformer
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 27/mai/2015
 */
final class RequestTransformer
        extends MessageToMessageDecoder<io.netty.handler.codec.http.HttpRequest> {

    @Override
    protected void decode(ChannelHandlerContext ctx,
            io.netty.handler.codec.http.HttpRequest request, List<Object> out) {
        out.add(RequestConverter.convert(request));
    }
    
}
