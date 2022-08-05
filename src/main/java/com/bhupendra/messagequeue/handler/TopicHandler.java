package com.bhupendra.messagequeue.handler;

import com.bhupendra.messagequeue.model.Topic;
import com.bhupendra.messagequeue.model.TopicSubscriber;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

public class TopicHandler {
    private final Topic topic;
    private final Map<String, SubscriberWorker> subcriberWorkerMap;

    public TopicHandler(@NonNull Topic topic){
        this.topic = topic;
        this.subcriberWorkerMap = new HashMap<>();
    }

    public void publish(){
        for(TopicSubscriber topicSubscriber : topic.getSubscribers()){

        }
    }

    public void startSubscriberWorker(@NonNull TopicSubscriber topicSubscriber){
        final  String subscriberId = topicSubscriber.getSubscriber().getId();
        if(!subcriberWorkerMap.containsKey(subscriberId)){
            final SubscriberWorker subscriberWorker = new SubscriberWorker(topic,topicSubscriber);
            subcriberWorkerMap.put(subscriberId,subscriberWorker);
            new Thread(subscriberWorker).start();
        }
        final SubscriberWorker subscriberWorker = subcriberWorkerMap.get(subscriberId);
        subscriberWorker.wakeUpIfNeeded();
    }
}
