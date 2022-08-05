package com.bhupendra.messagequeue.model;

import lombok.Getter;
import lombok.NonNull;


import java.util.ArrayList;
import java.util.List;

/**
 * Topic on which a message can be published
 */

@Getter
public class Topic {
    private final String topicId;
    private final String topicName;
    private final List<Message> messages;  // TODO: Change getter to send only immutable list
    private final List<TopicSubscriber> subscribers; // TODO: Change getter to send only immutable list

    public Topic(@NonNull final String topicId,@NonNull final String topicName){
        this.topicId = topicId;
        this.topicName = topicName;
        messages = new ArrayList<>();
        subscribers = new ArrayList<>();
    }

    public synchronized void addMessage(@NonNull final Message message){
        messages.add(message);
    }

    public synchronized  void addSubscriber(@NonNull TopicSubscriber subscriber){
        subscribers.add(subscriber);
    }
}
