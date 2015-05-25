
package io.bsonntag.neddy.http;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * HttpHeader
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 22/mai/2015
 */
public final class HttpHeader implements Iterable<HttpHeaderField> {
    
    private final Map<String, HttpHeaderField> fields;

    public HttpHeader(HttpHeaderField... fields) {
        this.fields = toMap(Arrays.stream(fields));
    }

    public HttpHeader(List<HttpHeaderField> fields) {
        this.fields = toMap(fields.stream());
    }
    
    public HttpHeaderField get(String name) {
        return fields.get(name);
    }
    
    public int size() {
        return fields.size();
    }

    @Override
    public Iterator<HttpHeaderField> iterator() {
        return fields.values().iterator();
    }
    
    private Map<String, HttpHeaderField> toMap(Stream<HttpHeaderField> fieldStream) {
        return fieldStream.collect(Collectors.toMap(
                HttpHeaderField::getName,
                Function.identity(),
                HttpHeaderField::merge,
                HashMap::new
        ));
    }
    
}
