package com.bhupendra.messagequeue.api;

import com.bhupendra.messagequeue.model.Message;

/**
 * Interface for subscribers
 */
public interface ISubscriber {
    String getId();
    void consume(Message message) throws InterruptedException;
}
