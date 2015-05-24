
package io.bsonntag.neddy.http;

import java.net.URI;

/**
 * HttpRequest
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 22/mai/2015
 */
public class HttpRequest {
    
    private final HttpMethod method;
    private final URI uri;
    private final HttpHeader header;
    private final HttpParams params;
    private final String content;

    public HttpRequest(HttpMethod method, URI uri, HttpHeader header,
            HttpParams params, String content) {
        this.method = method;
        this.uri = uri;
        this.header = header;
        this.params = params;
        this.content = content;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public URI getUri() {
        return uri;
    }
    
    public String getPath() {
        return uri.getPath();
    }
    
    public String getQuery() {
        return uri.getQuery();
    }

    public HttpHeader getHeader() {
        return header;
    }

    public HttpParams getParams() {
        return params;
    }

    public String getContent() {
        return content;
    }
    
}
