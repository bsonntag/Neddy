
package io.bsonntag.neddy.http;

/**
 * HttpResponse
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 22/mai/2015
 */
public class HttpResponse {
    
    private int status;
    private HttpHeader header;
    private String content;

    HttpResponse() {
        content = "";
    }

    public int getStatus() {
        return status;
    }

    public HttpHeader getHeader() {
        return header;
    }

    public String getContent() {
        return content;
    }
    
    public HttpResponse writeHead(int status) {
        this.status = status;
        return this;
    }
    
    public HttpResponse writeHead(int status, HttpHeader header) {
        this.status = status;
        this.header = header;
        return this;
    }
    
    public HttpResponse write(String content) {
        this.content += content;
        return this;
    }
    
}
