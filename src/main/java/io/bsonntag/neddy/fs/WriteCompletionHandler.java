
package io.bsonntag.neddy.fs;

import java.nio.channels.CompletionHandler;

/**
 * WriteCompletionHandler
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 25/mai/2015
 */
public class WriteCompletionHandler implements CompletionHandler<Integer, Object> {
    
    private final WriteHandler writeHandler;
    private final int amount;

    public WriteCompletionHandler(WriteHandler writeHandler, int amount) {
        this.writeHandler = writeHandler;
        this.amount = amount;
    }

    @Override
    public void completed(Integer result, Object attachment) {
        if(result < amount) {
            writeHandler.accept(new Exception("Couldn't write all data."));
        }
        else {
            writeHandler.accept(null);
        }
    }

    @Override
    public void failed(Throwable exc, Object attachment) {
        writeHandler.accept(exc);
    }
    
}
