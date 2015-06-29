
package io.bsonntag.neddy.fs;

import io.bsonntag.neddy.events.EventBus;
import io.bsonntag.neddy.events.EventListener;
import io.bsonntag.neddy.streams.AbstractReadableStream;
import io.bsonntag.neddy.streams.ReadableDataStream;
import io.bsonntag.neddy.streams.WritableStream;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;

/**
 * ReadableFileStream
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 6/jun/2015
 */
public final class ReadableFileStream extends ReadableDataStream {
    
    private final EventBus<Integer> onOpenEventBus;
    
    private final AsynchronousFileChannel fileChannel;
    private final Deque<ByteBuffer> queue;

    ReadableFileStream(AsynchronousFileChannel fileChannel, ExecutorService threadPool) {
        this.onOpenEventBus = new EventBus<>();
        this.fileChannel = fileChannel;
        this.queue = new ConcurrentLinkedDeque<>();
        
    }
    
    public ReadableFileStream onOpen(EventListener<Integer> listener) {
        onOpenEventBus.addListener(listener);
        return this;
    }

    @Override
    public ByteBuffer read(int size) {
        // TODO code application logic here
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ByteBuffer read() {
        // TODO code application logic here
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public AbstractReadableStream<ByteBuffer> pause() {
        // TODO code application logic here
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public AbstractReadableStream<ByteBuffer> resume() {
        // TODO code application logic here
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isPaused() {
        // TODO code application logic here
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <W extends WritableStream<ByteBuffer, W>> W pipe(W destination) {
        // TODO code application logic here
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public AbstractReadableStream<ByteBuffer> unpipe() {
        // TODO code application logic here
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <W extends WritableStream<ByteBuffer, W>> AbstractReadableStream<ByteBuffer> unpipe(W destination) {
        // TODO code application logic here
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected boolean push(ByteBuffer data) {
        // TODO code application logic here
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void doRead(int size) {
        // TODO code application logic here
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private void publishData(ByteBuffer buffer) {
        emitDataEvent(buffer);
        queue.add(buffer);
        emitReadableEvent();
    }
    
    private static class Reader implements Runnable {
        
        private ReadableFileStream stream;
        private int position;
        private AsynchronousFileChannel fileChannel;
    
        @Override
        public void run() {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            fileChannel.read(buffer, position, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer buffer) {
                    if(result < 0) {
                        handleEnd();
                    }
                    else {
                        handleRead(result, buffer);
                    }
                }
                
                private void handleEnd() {
                    
                }
                
                private void handleRead(int result, ByteBuffer buffer) {
                    stream.publishData(buffer);
                }

                @Override
                public void failed(Throwable exc, ByteBuffer buffer) {
                    // TODO code application logic here
                    throw new UnsupportedOperationException("Not supported yet.");
                }
            });
        }
        
    }
    
}
