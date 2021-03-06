
package io.bsonntag.neddy.events;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * EventBus
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 25/mai/2015
 * @param <T> the type of the objects passed on the events
 */
public final class EventBus<T> {
    
    private final List<EventListener<T>> eventListeners;

    public EventBus() {
        eventListeners = new ArrayList<>();
    }
    
    public List<EventListener<T>> getListeners() {
        return Collections.unmodifiableList(eventListeners);
    }
    
    public void addListener(EventListener<T> listener) {
        eventListeners.add(listener);
    }
    
    public void removeListener(EventListener<T> listener) {
        eventListeners.remove(listener);
    }
    
    public void removeAllListeners() {
        eventListeners.clear();
    }
    
    public boolean emit(T event) {
        if(eventListeners.isEmpty()) {
            return false;
        }
        else {
            eventListeners.stream().forEach((listener) -> listener.handle(event));
            return true;
        }
    }

}
