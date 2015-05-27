
package io.bsonntag.neddy.events;

import java.util.ArrayList;
import java.util.List;

/**
 * BiEventBus
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 27/mai/2015
 * @param <A>
 * @param <B>
 */
public final class BiEventBus<A, B> {
    
    private final List<BiEventListener<A, B>> eventListeners;

    public BiEventBus() {
        eventListeners = new ArrayList<>();
    }
    
    public void addListener(BiEventListener<A, B> listener) {
        eventListeners.add(listener);
    }
    
    public void removeListener(BiEventListener<A, B> listener) {
        eventListeners.remove(listener);
    }
    
    public void removeAllListeners() {
        eventListeners.clear();
    }
    
    public void emit(A a, B b) {
        eventListeners.stream().forEach((listener) -> listener.handle(a, b));
    }
    
}
