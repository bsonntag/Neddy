
package io.bsonntag.neddy;

import io.bsonntag.neddy.http.Http;
import io.bsonntag.neddy.http.HttpHeader;
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
        Http.createServer((req, res) -> {
            res.writeHead(200, new HttpHeader(contentType("text/html; charset=UTF-8")));
            res.write("Hello World!");
        }).listen(3000);
    }

}
