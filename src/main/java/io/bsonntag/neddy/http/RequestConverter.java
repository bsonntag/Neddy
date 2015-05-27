
package io.bsonntag.neddy.http;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.util.CharsetUtil;
import java.net.URI;
import java.util.Map.Entry;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * RequestConverter
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 22/mai/2015
 */
class RequestConverter {
    
    private static final Function<Entry<String,String>, HttpHeaderField> toHeaderField =
            header -> new HttpHeaderField(header.getKey(), header.getValue());
    
    static HttpRequest convert(io.netty.handler.codec.http.HttpRequest request) {
        return new RequestConverter(request).convert();
    }
    
    
    private final io.netty.handler.codec.http.HttpRequest nettyRequest;

    private RequestConverter(io.netty.handler.codec.http.HttpRequest nettyRequest) {
        this.nettyRequest = nettyRequest;
    }
    
    private HttpRequest convert() {
        return new HttpRequest(
            convertMethod(),
            convertUri(),
            convertHeader(),
            convertParameters(),
            convertContent()
        );
    }
    
    private HttpMethod convertMethod() {
        return HttpMethod.valueOf(nettyRequest.getMethod().name());
    }
    
    private URI convertUri() {
        return URI.create(nettyRequest.getUri());
    }
            
    private HttpHeader convertHeader() {
        return new HttpHeader(
                nettyRequest.headers().entries().stream()
                        .map(toHeaderField)
                        .collect(toList()));
    }
    
    private HttpParams convertParameters() {
        QueryStringDecoder decoder = new QueryStringDecoder(nettyRequest.getUri());
        return new HttpParams(decoder.parameters());
    }
    
    private String convertContent() {
        if(nettyRequest instanceof FullHttpRequest) {
            ByteBuf content = ((FullHttpRequest)nettyRequest).content();
            return content.toString(CharsetUtil.UTF_8);
        }
        else {
            return "";
        }
    }
    
}
