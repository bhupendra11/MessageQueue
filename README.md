# Message Queue

This is a simple implementation of a message queue supporting publisher-subscriber model like Kafka.

It supports following operations:

- It should support multiple topics on which messges can be published.
- Publisher can publish a message to a particular topic
- Subscriber can subscribe to a particular topic.
- Whenever a message is published to a particular topic, all the subscribers subscribed to the topic should receive the message.
- We should be able to reset the offset for a subscriber. this means subsriber would restart reading again from the offset.
   Its like replaying of messages.
- Subscribers should be able to run in parallel.


