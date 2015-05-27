
package io.bsonntag.neddy.http;

import io.bsonntag.neddy.events.BiEventBus;
import io.bsonntag.neddy.events.BiEventListener;
import io.bsonntag.neddy.http.events.DefaultHttpServerErrorListener;

/**
 * HttpEventBus
 * 
 * TODO: make this class threadsafe?
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 27/mai/2015
 */
final class HttpEventBus {
    
    private final BiEventBus<HttpRequest, HttpResponse> requestEventBus;
    private final BiEventBus<Throwable, HttpResponse> errorEventBus;

    HttpEventBus() {
        requestEventBus = new BiEventBus<>();
        errorEventBus = new BiEventBus<>();
    }
    
    void onRequest(BiEventListener<HttpRequest, HttpResponse> listener) {
        requestEventBus.addListener(listener);
    }
    
    void emitRequestEvent(HttpRequest request, HttpResponse response) {
        requestEventBus.emit(request, response);
    }
    
    void onServerError(BiEventListener<Throwable, HttpResponse> listener) {
        errorEventBus.addListener(listener);
    }
    
    void emitServerErrorEvent(Throwable exception, HttpResponse response) {
        boolean emitted = errorEventBus.emit(exception, response);
        if(!emitted) {
            DefaultHttpServerErrorListener.get().handle(exception, response);
        }
    }
    
}
