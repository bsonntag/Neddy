
package io.bsonntag.neddy.fs;

import java.nio.ByteBuffer;
import java.util.function.BiConsumer;

/**
 * ReadHandler
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 23/mai/2015
 */
public interface ReadHandler extends BiConsumer<ByteBuffer, Throwable> {

    @Override
    public void accept(ByteBuffer t, Throwable u);
    
}
