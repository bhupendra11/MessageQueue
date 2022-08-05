package com.bhupendra.messagequeue.api;

/**
 * Interface for subscribers
 */
public interface ISubscriber {
    String getId();
    void consume(String message) throws InterruptedException;
}
