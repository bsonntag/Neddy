
package io.bsonntag.neddy.streams;

import io.bsonntag.neddy.events.EventBus;
import io.bsonntag.neddy.events.EventListener;

/**
 * AbstractReadableStream
 * 
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 6/jun/2015
 * @param <T>
 */
public abstract class AbstractReadableStream<T>
        implements ReadableStream<T, AbstractReadableStream<T>> {
    
    private final EventBus<AbstractReadableStream<T>> onReadableEventBus;
    private final EventBus<T> onDataEventBus;
    private final EventBus<AbstractReadableStream<T>> onEndEventBus;
    private final EventBus<AbstractReadableStream<T>> onCloseEventBus;
    private final EventBus<Throwable> onErrorEventBus;

    public AbstractReadableStream() {
        onReadableEventBus = new EventBus<>();
        onDataEventBus = new EventBus<>();
        onEndEventBus = new EventBus<>();
        onCloseEventBus = new EventBus<>();
        onErrorEventBus = new EventBus<>();
    }

    @Override
    public AbstractReadableStream<T> onReadable(EventListener<AbstractReadableStream<T>> listener) {
        onReadableEventBus.addListener(listener);
        return this;
    }

    @Override
    public AbstractReadableStream<T> onData(EventListener<T> listener) {
        onDataEventBus.addListener(listener);
        return this;
    }

    @Override
    public AbstractReadableStream<T> onEnd(EventListener<AbstractReadableStream<T>> listener) {
        onEndEventBus.addListener(listener);
        return this;
    }

    @Override
    public AbstractReadableStream<T> onClose(EventListener<AbstractReadableStream<T>> listener) {
        onCloseEventBus.addListener(listener);
        return this;
    }

    @Override
    public AbstractReadableStream<T> onError(EventListener<Throwable> listener) {
        onErrorEventBus.addListener(listener);
        return this;
    }
    
    protected final void emitReadableEvent() {
        onReadableEventBus.emit(this);
    }
    
    protected final void emitDataEvent(T data) {
        onDataEventBus.emit(data);
    }
    
    protected final void emitEndEvent() {
        onEndEventBus.emit(this);
    }
    
    protected final void emitCloseEvent() {
        onCloseEventBus.emit(this);
    }
    
    protected final void emitErrorEvent(Throwable error) {
        onErrorEventBus.emit(error);
    }
    
    protected final void emitErrorEvent(String errorMessage) {
        onErrorEventBus.emit(new Exception(errorMessage));
    }
    
    protected abstract boolean push(T data);
    
    protected abstract void doRead(int size);
    
}
