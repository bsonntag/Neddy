
package io.bsonntag.neddy.streams;

import io.bsonntag.neddy.events.EventListener;

/**
 * WritableStream
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 3/jun/2015
 * @param <T>
 */
public interface WritableStream<T, S extends WritableStream<T, S>> {
    
    public S onDrain(EventListener<S> listener);
    
    public S onFinish(EventListener<S> listener);
    
    public S onPipe(EventListener<ReadableStream<T, ?>> listener);
    
    public S onUnpipe(EventListener<ReadableStream<T, ?>> listener);
    
    public S onError(EventListener<Throwable> listener);
    
    public boolean write(T data);
    
    public boolean write(T data, EventListener<S> listener);
    
    public S cork();
    
    public S uncork();
    
    public void end();
    
    public void end(T data);
    
    public void end(EventListener<S> listener);
    
    public void end(T data, EventListener<S> listener);
    
}
