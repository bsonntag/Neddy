
package io.bsonntag.neddy.events;

/**
 * BiEventListener
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 26/mai/2015
 * @param <A> the type of the first object passed on the events
 * @param <B> the type of the second object passed on the events
 */
public interface BiEventListener<A, B> {
    
    public void handle(A object1, B object2);
    
}
