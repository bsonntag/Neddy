
package io.bsonntag.neddy.http;

import java.util.function.BiConsumer;

/**
 * HttpHandler
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 22/mai/2015
 */
@FunctionalInterface
public interface HttpHandler extends BiConsumer<HttpRequest, HttpResponse> {
    @Override
    public void accept(HttpRequest t, HttpResponse u);
}
