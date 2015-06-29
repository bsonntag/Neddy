
package io.bsonntag.neddy.streams;

/**
 * ReadableState
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 6/jun/2015
 */
final class ReadableState {
    
    private final int highWaterMark;
    
    // buffer
    private int length;
    // pipes
    // pipeCount
    private boolean flowing;
    
    private boolean ended;
    private boolean endEmitted;
    private boolean reading;
    
    private boolean needReadable;
    private boolean readableEmitted;
    private boolean readableListening;
    
    private boolean ranOut;
    
    private int awaitingDrain;
    
    private boolean readingMore;

    ReadableState(int highWaterMark) {
        this.highWaterMark = highWaterMark;
        this.length = 0;
        this.flowing = false;
        this.ended = false;
        this.endEmitted = false;
        this.reading = false;
        this.needReadable = false;
        this.readableEmitted = false;
        this.readableListening = false;
        this.ranOut = false;
        this.awaitingDrain = 0;
        this.readingMore = false;
    }

    int getLength() {
        return length;
    }

    void setLength(int length) {
        this.length = length;
    }
    
    void addLength(int length) {
        this.length += length;
    }

    boolean isFlowing() {
        return flowing;
    }

    void setFlowing(boolean flowing) {
        this.flowing = flowing;
    }

    boolean isEnded() {
        return ended;
    }

    void setEnded(boolean ended) {
        this.ended = ended;
    }

    boolean isEndEmitted() {
        return endEmitted;
    }

    void setEndEmitted(boolean endEmitted) {
        this.endEmitted = endEmitted;
    }

    boolean isReading() {
        return reading;
    }

    void setReading(boolean reading) {
        this.reading = reading;
    }

    boolean needsReadable() {
        return needReadable;
    }

    void setNeedReadable(boolean needReadable) {
        this.needReadable = needReadable;
    }

    boolean isReadableEmitted() {
        return readableEmitted;
    }

    void setReadableEmitted(boolean readableEmitted) {
        this.readableEmitted = readableEmitted;
    }

    boolean isReadingMore() {
        return readingMore;
    }

    void setReadingMore(boolean readingMore) {
        this.readingMore = readingMore;
    }
    
    boolean needDataNow() {
        return flowing && length == 0;
    }
    
    boolean needsMoreData() {
        return !ended && (needReadable || length < highWaterMark || length == 0);
    }
    
}
