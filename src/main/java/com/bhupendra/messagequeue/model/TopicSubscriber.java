package com.bhupendra.messagequeue.model;

import com.bhupendra.messagequeue.api.ISubscriber;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@AllArgsConstructor
public class TopicSubscriber{
    private final AtomicInteger offset;
    private final  ISubscriber subscriber;

    public TopicSubscriber(@NonNull ISubscriber subscriber){
        this.subscriber = subscriber;
        this.offset = new AtomicInteger(0);
    }
}
