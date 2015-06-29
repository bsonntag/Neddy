
package io.bsonntag.neddy.streams;

import io.bsonntag.neddy.events.EventBus;
import io.bsonntag.neddy.events.EventListener;

/**
 * AbstractWritableStream
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 6/jun/2015
 */
public abstract class AbstractWritableStream<T>
        implements WritableStream<T, AbstractWritableStream<T>> {
    
    private final EventBus<AbstractWritableStream<T>> onDrainEventBus;
    private final EventBus<AbstractWritableStream<T>> onFinishEventBus;
    private final EventBus<ReadableStream<T, ?>> onPipeEventBus;
    private final EventBus<ReadableStream<T, ?>> onUnpipeEventBus;
    private final EventBus<Throwable> onErrorEventBus;

    public AbstractWritableStream() {
        onDrainEventBus = new EventBus<>();
        onFinishEventBus = new EventBus<>();
        onPipeEventBus = new EventBus<>();
        onUnpipeEventBus = new EventBus<>();
        onErrorEventBus = new EventBus<>();
    }

    @Override
    public AbstractWritableStream<T> onDrain(EventListener<AbstractWritableStream<T>> listener) {
        onDrainEventBus.addListener(listener);
        return this;
    }

    @Override
    public AbstractWritableStream<T> onFinish(EventListener<AbstractWritableStream<T>> listener) {
        onFinishEventBus.addListener(listener);
        return this;
    }

    @Override
    public AbstractWritableStream<T> onPipe(EventListener<ReadableStream<T, ?>> listener) {
        onPipeEventBus.addListener(listener);
        return this;
    }

    @Override
    public AbstractWritableStream<T> onUnpipe(EventListener<ReadableStream<T, ?>> listener) {
        onUnpipeEventBus.addListener(listener);
        return this;
    }

    @Override
    public AbstractWritableStream<T> onError(EventListener<Throwable> listener) {
        onErrorEventBus.addListener(listener);
        return this;
    }
    
}
