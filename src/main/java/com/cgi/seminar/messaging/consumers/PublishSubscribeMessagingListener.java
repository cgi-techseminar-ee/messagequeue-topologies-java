package com.cgi.seminar.messaging.consumers;

import com.cgi.seminar.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


@Component
public class PublishSubscribeMessagingListener {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @RabbitListener(queues = {"demo-pubsub-queue1"}) // MessagingConfiguration.PUBLISH_SUBSCRIBE_QUEUE1_NAME
    void onQueue1MessageReceived(Message<Person> message) {
        Person person = message.getPayload();
        log.info("Received message from publish/subscribe queue 1: {}", person);
    }

    @RabbitListener(queues = {"demo-pubsub-queue2"}) // MessagingConfiguration.PUBLISH_SUBSCRIBE_QUEUE2_NAME
    void onQueue2MessageReceived(Message<Person> message) {
        Person person = message.getPayload();
        log.info("Received message from publish/subscribe queue 2: {}", person);
    }
}
