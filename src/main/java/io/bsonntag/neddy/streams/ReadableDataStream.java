
package io.bsonntag.neddy.streams;

import java.nio.ByteBuffer;
import java.util.Deque;
import java.util.LinkedList;

/**
 * ReadableDataStream
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 6/jun/2015
 */
public abstract class ReadableDataStream extends AbstractReadableStream<ByteBuffer> {
    
    private final Deque<ByteBuffer> queue;

    public ReadableDataStream() {
        this.queue = new LinkedList<>();
    }
    
    public abstract ByteBuffer read(int size);
    
}
