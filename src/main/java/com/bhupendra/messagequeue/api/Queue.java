package com.bhupendra.messagequeue.api;

import com.bhupendra.messagequeue.handler.TopicHandler;
import com.bhupendra.messagequeue.model.Message;
import com.bhupendra.messagequeue.model.Topic;

import com.bhupendra.messagequeue.model.TopicSubscriber;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * Main public facing class which exposes different functionalities of this messaging queue
 */
public class Queue {
    private final Map<String, TopicHandler> topicHandlerMap;

    public Queue(){
        this.topicHandlerMap = new HashMap<>();
    }

    public Topic createTopic(@NonNull final String topicName){
        final Topic topic = new Topic(UUID.randomUUID().toString(),topicName);
        TopicHandler topicHandler = new TopicHandler(topic);
        topicHandlerMap.put(topic.getTopicId(),topicHandler);
        System.out.println("Created topic : "+topic.getTopicName());
        return topic;
    }

    public void subscribe(@NonNull final Topic topic, @NonNull final ISubscriber subscriber){
        topic.addSubscriber(new TopicSubscriber(subscriber));
        System.out.println(subscriber.getId()+" subscribed to topic : "+topic.getTopicName());
    }

    public void publish(@NonNull final Topic topic, @NonNull final Message message){
        topic.addMessage(message);
        System.out.println(message.getMsg()+" published to topic: "+topic.getTopicName());
        new Thread(()-> topicHandlerMap.get(topic.getTopicId()).publish()).start();
    }

    public void resetOffset(@NonNull final Topic topic, @NonNull final ISubscriber subscriber,
                            final Integer newOffset){
        for (TopicSubscriber topicSubscriber : topic.getSubscribers()){
            if(topicSubscriber.getSubscriber().equals(subscriber)){
                topicSubscriber.getOffset().set(newOffset);
                System.out.println(topicSubscriber.getSubscriber().getId()+" offset set to : "+newOffset);
                new Thread(()-> topicHandlerMap.get(topic.getTopicId()).startSubscriberWorker(topicSubscriber)).start();
            }
        }
    }
}
