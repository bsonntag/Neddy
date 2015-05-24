
package io.bsonntag.neddy.http;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * HttpHeader
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 22/mai/2015
 */
public final class HttpHeader implements Iterable<HttpHeaderField> {
    
    private final Map<String, HttpHeaderField> fields;

    public HttpHeader(HttpHeaderField... fields) {
        this.fields = new HashMap<>();
        for(HttpHeaderField field : fields) {
            this.fields.compute(field.getName(),
                    (k, v) -> v == null ? field : v.merge(field));
        }
    }

    public HttpHeader(List<HttpHeaderField> fields) {
        this.fields = fields.stream().collect(toMap());
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
    
    private Collector<HttpHeaderField, ?, Map<String, HttpHeaderField>> toMap() {
        return Collectors.toMap(
                HttpHeaderField::getName,
                Function.identity(),
                HttpHeaderField::merge,
                HashMap::new
        );
    }
    
}
