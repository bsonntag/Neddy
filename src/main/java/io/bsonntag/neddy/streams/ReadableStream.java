
package io.bsonntag.neddy.streams;

import io.bsonntag.neddy.events.EventListener;

/**
 * ReadableStream
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 30/mai/2015
 * @param <T>
 * @param <S>
 */
public interface ReadableStream<T, S extends ReadableStream<T, S>> {
    
    public S onReadable(EventListener<S> listener);
    
    public S onData(EventListener<T> listener);
    
    public S onEnd(EventListener<S> listener);
    
    public S onClose(EventListener<S> listener);
    
    public S onError(EventListener<Throwable> listener);
    
    public T read();
    
    public S pause();
    
    public S resume();
    
    public boolean isPaused();
    
    public <W extends WritableStream<T, W>> W pipe(W destination);
    
    public S unpipe();
    
    public <W extends WritableStream<T, W>> S unpipe(W destination);
    
}
