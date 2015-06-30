
package io.bsonntag.neddy.http.routing;

import io.bsonntag.neddy.events.BiEventListener;
import io.bsonntag.neddy.http.HttpMethod;
import io.bsonntag.neddy.http.HttpRequest;
import io.bsonntag.neddy.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Application
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 30/jun/2015
 */
public final class Application implements BiEventListener<HttpRequest, HttpResponse> {
    
    private final Map<String, Router> listeners;

    public Application() {
        listeners = new HashMap<>();
    }

    @Override
    public void handle(HttpRequest request, HttpResponse response) {
        HttpMethod method = request.getMethod();
        String path = request.getPath();
        listeners.get(path)
                .handle(method, request, response);
    }
    
    public Application onGet(String path, BiEventListener<HttpRequest, HttpResponse> listener) {
        listeners.computeIfAbsent(path, Router::new)
                .onGet(listener);
        return this;
    }
    
    public Application onPost(String path, BiEventListener<HttpRequest, HttpResponse> listener) {
        listeners.computeIfAbsent(path, Router::new)
                .onPost(listener);
        return this;
    }
    
    public Application onPatch(String path, BiEventListener<HttpRequest, HttpResponse> listener) {
        listeners.computeIfAbsent(path, Router::new)
                .onPatch(listener);
        return this;
    }
    
    public Application onPut(String path, BiEventListener<HttpRequest, HttpResponse> listener) {
        listeners.computeIfAbsent(path, Router::new)
                .onPut(listener);
        return this;
    }
    
    public Application onDelete(String path, BiEventListener<HttpRequest, HttpResponse> listener) {
        listeners.computeIfAbsent(path, Router::new)
                .onDelete(listener);
        return this;
    }
    
    public Router router(String path) {
        return listeners.computeIfAbsent(path, Router::new);
    }
    
    public Application use(String path, BiEventListener<HttpRequest, HttpResponse> listener) {
        listeners.computeIfAbsent(path, Router::new)
                .onGet(listener)
                .onPost(listener)
                .onPatch(listener)
                .onPut(listener)
                .onDelete(listener);
        return this;
    }
    
    public Application use(BiEventListener<HttpRequest, HttpResponse> listener) {
        
        return this;
    }
    
}
