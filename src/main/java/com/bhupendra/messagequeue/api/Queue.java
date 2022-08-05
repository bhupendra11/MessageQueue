package com.bhupendra.messagequeue.api;

import com.bhupendra.messagequeue.model.Topic;
import com.sun.istack.internal.NotNull;

/**
 * Main public facing class which exposes different functionalities of this messaging queue
 */
public class Queue {

    public Queue(){

    }

    public Topic createTopic(@NotNull final String topicName){
        return null;
    }

    public void subscribe(@NotNull final Topic topic, @NotNull final ISubscriber subscriber){

    }

    public void publish(@NotNull final Topic topic, @NotNull final String message){

    }

    public void resetOffset(@NotNull final Topic topic, @NotNull final ISubscriber subscriber,
                            final Integer newOffset){

    }
}
