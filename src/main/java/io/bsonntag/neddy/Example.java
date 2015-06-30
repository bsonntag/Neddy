
package io.bsonntag.neddy;

import io.bsonntag.neddy.http.Http;
import io.bsonntag.neddy.http.HttpHeader;
import io.bsonntag.neddy.http.routing.Application;
import java.io.IOException;

import static io.bsonntag.neddy.http.HttpHeaderField.contentType;

/**
 * Example
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 22/mai/2015
 */
public class Example {

    public static void main(String[] args) throws IOException {
        example1();
        example2();
    }

    private static void example1() {
        Http.createServer((req, res) -> {
            System.out.println("request");
            System.out.println(req.getPath());
            res.writeHead(200, new HttpHeader(contentType("text/plain; charset=UTF-8")));
            res.write("Hello World!");
            res.end();
        }).listen(3000);
    }

    private static void example2() {
        Application app = new Application();
        app.onGet("/", (req, res) -> {
            res.writeHead(200, new HttpHeader(contentType("text/plain; charset=UTF-8")));
            res.write("Hello root!");
            res.end();
        });
        Http.createServer(app)
            .listen(3001);
    }

}
