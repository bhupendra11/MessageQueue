package com.bhupendra.messagequeue;

import com.bhupendra.messagequeue.api.Queue;
import com.bhupendra.messagequeue.handler.SleepingSubscriber;
import com.bhupendra.messagequeue.model.Message;
import com.bhupendra.messagequeue.model.Topic;

public class Driver {

    public static void main(String[] args) throws InterruptedException {
        final Queue queue = new Queue();
        final Topic topic1 = queue.createTopic("t1");
        final Topic topic2 = queue.createTopic("t2");

        final SleepingSubscriber subscriber1 = new SleepingSubscriber("sub1",10000);
        final SleepingSubscriber subscriber2 = new SleepingSubscriber("sub2",10000);

        queue.subscribe(topic1,subscriber1);
        queue.subscribe(topic2,subscriber2);

        final SleepingSubscriber subscriber3 = new SleepingSubscriber("sub3",5000);
        queue.subscribe(topic2,subscriber3);

        queue.publish(topic1,new Message("m1"));
        queue.publish(topic1,new Message("m2"));

        queue.publish(topic2,new Message("m3"));

        Thread.sleep(15000);
        queue.publish(topic2, new Message("m4"));
        queue.publish(topic1, new Message("m5"));

        queue.resetOffset(topic1,subscriber1,0);
    }
}
