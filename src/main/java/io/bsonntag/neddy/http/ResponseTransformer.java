
package io.bsonntag.neddy.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import java.util.List;

/**
 * ResponseTransformer
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 27/mai/2015
 */
final class ResponseTransformer extends MessageToMessageEncoder<HttpResponse> {

    @Override
    protected void encode(ChannelHandlerContext ctx, HttpResponse response,
            List<Object> out) {
        out.add(ResponseConverter.convert(response));
    }
    
}
