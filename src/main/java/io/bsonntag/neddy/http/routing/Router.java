
package io.bsonntag.neddy.http.routing;

import io.bsonntag.neddy.events.BiEventListener;
import io.bsonntag.neddy.http.HttpMethod;
import io.bsonntag.neddy.http.HttpRequest;
import io.bsonntag.neddy.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Router
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 30/jun/2015
 */
public final class Router {
        
    private final String path;
    private final Map<HttpMethod, List<BiEventListener<HttpRequest, HttpResponse>>> listeners;

    public Router(String path) {
        this.path = path;
        this.listeners = new HashMap<>();
    }

    public String getPath() {
        return path;
    }

    public Router onGet(BiEventListener<HttpRequest, HttpResponse> listener) {
        listeners.computeIfAbsent(HttpMethod.GET, method -> new ArrayList<>())
                .add(listener);
        return this;
    }

    public Router onPost(BiEventListener<HttpRequest, HttpResponse> listener) {
        listeners.computeIfAbsent(HttpMethod.POST, method -> new ArrayList<>())
                .add(listener);
        return this;
    }

    public Router onPatch(BiEventListener<HttpRequest, HttpResponse> listener) {
        listeners.computeIfAbsent(HttpMethod.PATCH, method -> new ArrayList<>())
                .add(listener);
        return this;
    }

    public Router onPut(BiEventListener<HttpRequest, HttpResponse> listener) {
        listeners.computeIfAbsent(HttpMethod.PUT, method -> new ArrayList<>())
                .add(listener);
        return this;
    }

    public Router onDelete(BiEventListener<HttpRequest, HttpResponse> listener) {
        listeners.computeIfAbsent(HttpMethod.DELETE, method -> new ArrayList<>())
                .add(listener);
        return this;
    }

    public Router remove(HttpMethod method) {
        listeners.remove(method);
        return this;
    }

    public Router remove(HttpMethod method, BiEventListener<HttpRequest, HttpResponse> listener) {
        listeners.get(method)
                .remove(listener);
        return this;
    }

    void handle(HttpMethod method, HttpRequest request, HttpResponse response) {
        List<BiEventListener<HttpRequest, HttpResponse>> methodListeners = listeners.get(method);
        
        methodListeners.stream()
                .filter(listener -> {
                    listener.handle(request, response);
                    return response.isEnded();
                })
                .findFirst();
    }
    
}
