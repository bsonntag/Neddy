
package io.bsonntag.neddy.fs;

import java.util.function.Consumer;

/**
 * WriteHandler
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 25/mai/2015
 */
public interface WriteHandler extends Consumer<Throwable> {

    @Override
    public void accept(Throwable t);
    
}
