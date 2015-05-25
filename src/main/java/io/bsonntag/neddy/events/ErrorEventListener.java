
package io.bsonntag.neddy.events;

/**
 * ErrorEventListener
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 26/mai/2015
 */
@FunctionalInterface
public interface ErrorEventListener extends EventListener<Throwable >{
    
    @Override
    public void handle(Throwable exception);
    
}
