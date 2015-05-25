
package io.bsonntag.neddy.fs;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Files
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 23/mai/2015
 */
public final class Files {

    private Files() { }
    
    private static final ExecutorService threadPool = Executors.newSingleThreadExecutor();
    private static final Set<OpenOption> readOptions = setWith(StandardOpenOption.READ);
    private static final Set<OpenOption> writeOptions = setWith(
            StandardOpenOption.CREATE,
            StandardOpenOption.TRUNCATE_EXISTING,
            StandardOpenOption.WRITE
    );
    private static final Set<OpenOption> appendOptions = setWith(
            StandardOpenOption.CREATE,
            StandardOpenOption.APPEND
    );
    
    public static void readFile(String path, ReadHandler handler) {
        try {
            readFile(Paths.get(path), handler);
        }
        catch(IOException ex) {
            handler.accept(null, ex);
        }
    }

    private static void readFile(Path path, ReadHandler handler) throws IOException {
        AsynchronousFileChannel fileChannel = open(path, readOptions);
        long size = fileChannel.size();
        if(size <= Integer.MAX_VALUE) {
            readSmallFile(fileChannel, (int)size, handler);
        }
        else {
            throw new IllegalArgumentException("File is too big.");
        }
    }

    private static void readSmallFile(AsynchronousFileChannel fileChannel, int size,
            ReadHandler handler) {
        ByteBuffer buffer = ByteBuffer.allocate(size);
        fileChannel.read(buffer, 0, null,
                new SmallReadCompletionHandler(handler, buffer, size));
    }
    
    public static void writeFile(String path, ByteBuffer data, WriteHandler handler) {
        try {
            writeFile(Paths.get(path), data, handler);
        }
        catch(IOException ex) {
            handler.accept(ex);
        }
    }
    
    private static void writeFile(Path path, ByteBuffer data, WriteHandler handler)
            throws IOException {
        AsynchronousFileChannel fileChannel = open(path, writeOptions);
        fileChannel.write(data, 0, null,
                new WriteCompletionHandler(handler, data.remaining()));
    }
    
    private static AsynchronousFileChannel open(Path path, Set<OpenOption> options)
            throws IOException {
        return AsynchronousFileChannel.open(path, options, threadPool);
    }
    
    private static <T> Set<T> setWith(T... elements) {
        return new HashSet<>(Arrays.asList(elements));
    }
    
}
