
package io.bsonntag.neddy.http.events;

import io.bsonntag.neddy.events.BiEventListener;
import io.bsonntag.neddy.http.HttpHeader;
import io.bsonntag.neddy.http.HttpResponse;

import static io.bsonntag.neddy.http.HttpHeaderField.contentType;

/**
 * DefaultHttpServerErrorListener
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 27/mai/2015
 */
public final class DefaultHttpServerErrorListener
        implements BiEventListener<Throwable, HttpResponse> {
    
    private static final DefaultHttpServerErrorListener DEFAULT_LISTENER =
            new DefaultHttpServerErrorListener();
    
    public static BiEventListener<Throwable, HttpResponse> get() {
        return DEFAULT_LISTENER;
    }

    @Override
    public void handle(Throwable exception, HttpResponse response) {
        response.writeHead(500, new HttpHeader(contentType("text/plain")));
        response.write(exception.toString());
    }
    
}
