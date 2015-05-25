
package io.bsonntag.neddy.http;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * HttpHeaderField
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 22/mai/2015
 */
public final class HttpHeaderField {
    
    public static HttpHeaderField accept(String accept) {
        return new HttpHeaderField("Accept", accept);
    }
    
    public static HttpHeaderField authorization(String authorization) {
        return new HttpHeaderField("Authorization", authorization);
    }
    
    public static HttpHeaderField contentType(String contentType) {
        return new HttpHeaderField("Content-Type", contentType);
    }
    
    public static HttpHeaderField contentLength(int contentLength) {
        return new HttpHeaderField("Content-Length", Integer.toString(contentLength));
    }
    

    private final String name;

    private final List<String> values;

    public HttpHeaderField(String name, String value) {
        this.name = name;
        this.values = Arrays.stream(value.split(";"))
                .map(v -> v.trim())
                .collect(toArrayList());
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
        return new HttpHeaderField(getName(), merge(getValues(), other.getValues()));
    }
    
    private <T> List<T> merge(List<T> l1, List<T> l2) {
        return Stream.concat(l1.stream(), l2.stream()).collect(toArrayList());
    }
    
    private <T> Collector<T, ?, List<T>> toArrayList() {
        return Collectors.toCollection(ArrayList::new);
    }
    
}
