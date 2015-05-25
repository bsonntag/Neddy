
package io.bsonntag.neddy.events;

/**
 * EventListener
 * 
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 25/mai/2015
 * @param <T> the type of the object passed on the events
 */
@FunctionalInterface
public interface EventListener<T> {

    public void handle(T object);

}
