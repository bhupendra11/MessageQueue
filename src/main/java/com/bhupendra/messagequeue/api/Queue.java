package com.bhupendra.messagequeue.api;

import com.bhupendra.messagequeue.model.Topic;

import lombok.NonNull;


/**
 * Main public facing class which exposes different functionalities of this messaging queue
 */
public class Queue {

    public Queue(){

    }

    public Topic createTopic(@NonNull final String topicName){
        return null;
    }

    public void subscribe(@NonNull final Topic topic, @NonNull final ISubscriber subscriber){

    }

    public void publish(@NonNull final Topic topic, @NonNull final String message){

    }

    public void resetOffset(@NonNull final Topic topic, @NonNull final ISubscriber subscriber,
                            final Integer newOffset){

    }
}
