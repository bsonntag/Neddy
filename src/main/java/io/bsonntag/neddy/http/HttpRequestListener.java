
package io.bsonntag.neddy.http;

import io.bsonntag.neddy.events.BiEventListener;

/**
 * HttpRequestListener
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 22/mai/2015
 */
@FunctionalInterface
public interface HttpRequestListener extends BiEventListener<HttpRequest, HttpResponse> {
    
    @Override
    public void handle(HttpRequest request, HttpResponse response);
    
}
