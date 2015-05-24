
package io.bsonntag.neddy.http;

import java.util.AbstractList;
import java.util.List;
import java.util.Map;

/**
 * HttpParams
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 22/mai/2015
 */
public final class HttpParams {
    
    private final Map<String, List<String>> params;

    HttpParams(Map<String, List<String>> params) {
        this.params = params;
    }
    
    public String get(String name) {
        return getValuesOrNulls(name).get(0);
    }
    
    public String get(String name, int index) {
        return getValues(name).get(index);
    }
    
    public String getOrDefault(String name, String defaultValue) {
        return getValuesOrDefault(name, defaultValue).get(0);
    }
    
    public String getOrDefault(String name, int index, String defaultValue) {
        return getValuesOrDefault(name, defaultValue).get(index);
    }
    
    public Integer getInt(String name) {
        return getIntOrDefault(name, 0, null);
    }
    
    public Integer getInt(String name, int index) {
        return getIntOrDefault(name, index, null);
    }
    
    public Integer getIntOrDefault(String name, Integer defaultValue) {
        return getIntOrDefault(name, 0, defaultValue);
    }
    
    public Integer getIntOrDefault(String name, int index, Integer defaultValue) {
        if(contains(name, index)) {
            return Integer.parseInt(getValues(name).get(index));
        }
        else {
            return defaultValue;
        }
    }
    
    public Float getFloat(String name) {
        return getFloatOrDefault(name, 0, null);
    }
    
    public Float getFloat(String name, int index) {
        return getFloatOrDefault(name, index, null);
    }
    
    public Float getFloatOrDefault(String name, Float defaultValue) {
        return getFloatOrDefault(name, 0, defaultValue);
    }
    
    public Float getFloatOrDefault(String name, int index, Float defaultValue) {
        if(contains(name, index)) {
            return Float.valueOf(getValues(name).get(index));
        }
        else {
            return defaultValue;
        }
    }
    
    public List<String> getValues(String name) {
        return params.get(name);
    }
    
    public boolean contains(String name) {
        return params.containsKey(name);
    }
    
    public boolean contains(String name, int index) {
        return params.containsKey(name) &&
               params.get(name).size() > index;
    }
    
    private List<String> getValuesOrNulls(String name) {
        return getValuesOrDefault(name, null);
    }
    
    private List<String> getValuesOrDefault(String name, String defaultValue) {
        return params.getOrDefault(name, new AbstractList<String>() {
            @Override
            public String get(int index) {
                return defaultValue;
            }
            @Override
            public int size() {
                return 0;
            }
        });
    }
    
}
