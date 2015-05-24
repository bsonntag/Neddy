
package io.bsonntag.neddy.http;

import java.util.ArrayList;
import java.util.List;

/**
 * HttpHeaderField
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 22/mai/2015
 */
public final class HttpHeaderField {
    
    public static HttpHeaderField contentType(String contentType) {
        return new HttpHeaderField("Content-Type", contentType);
    }
    

    private final String name;

    private final List<String> values;

    public HttpHeaderField(String name, String value) {
        this.name = name;
        this.values = new ArrayList<>();
        this.values.add(value);
    }

    public HttpHeaderField(String name, List<String> values) {
        this.name = name;
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return getValue(0);
    }
    
    public String getValue(int index) {
        return values.get(index);
    }
    
    public List<String> getValues() {
        return values;
    }
    
    public HttpHeaderField merge(HttpHeaderField other) {
        List<String> values = new ArrayList<>(getValues());
        values.addAll(other.getValues());
        return new HttpHeaderField(getName(), values);
    }
    
}
