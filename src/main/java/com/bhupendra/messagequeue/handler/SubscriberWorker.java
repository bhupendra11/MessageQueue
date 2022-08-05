package com.bhupendra.messagequeue.handler;

import com.bhupendra.messagequeue.model.Message;
import com.bhupendra.messagequeue.model.Topic;
import com.bhupendra.messagequeue.model.TopicSubscriber;
import lombok.NonNull;
import lombok.SneakyThrows;

public class SubscriberWorker implements Runnable {

    private Topic topic;
    private TopicSubscriber topicSubscriber;

    public SubscriberWorker(@NonNull final Topic topic, @NonNull final TopicSubscriber topicSubscriber){
        this.topic = topic;
        this.topicSubscriber = topicSubscriber;
    }

    @SneakyThrows
    @Override
    public void run() {
        synchronized (topicSubscriber){
            do {
                int currOffset = topicSubscriber.getOffset().get();
                while(currOffset >= topic.getMessages().size()){
                    topicSubscriber.wait();
                }
                Message message = topic.getMessages().get(currOffset);
                topicSubscriber.getSubscriber().consume(message);

                //We canot just increment here since the subscriber offset may have been reset while consuming,
                //SO compare and set only if the value is the currentOffset
                topicSubscriber.getOffset().compareAndSet(currOffset,currOffset+1);
            }while (true);
        }
    }

    public synchronized void wakeUpIfNeeded(){
        synchronized (topicSubscriber){
            topicSubscriber.notify();
        }
    }
}
