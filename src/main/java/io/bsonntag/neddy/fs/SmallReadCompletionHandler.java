
package io.bsonntag.neddy.fs;

import java.nio.ByteBuffer;
import java.nio.channels.CompletionHandler;

/**
 * SmallReadCompletionHandler
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 23/mai/2015
 */
final class SmallReadCompletionHandler implements CompletionHandler<Integer, Object> {
    
    private final ReadHandler readHandler;
    private final ByteBuffer buffer;
    private final int amount;
    
    SmallReadCompletionHandler(ReadHandler readHandler, ByteBuffer buffer, int amount) {
        this.readHandler = readHandler;
        this.buffer = buffer;
        this.amount = amount;
    }

    @Override
    public void completed(Integer result, Object attachment) {
        if(result < amount) {
            readHandler.accept(buffer, new Exception("Couldn't read all file."));
        }
        else {
            readHandler.accept(buffer, null);
        }
    }

    @Override
    public void failed(Throwable exception, Object attachment) {
        readHandler.accept(buffer, exception);
    }
    
}
