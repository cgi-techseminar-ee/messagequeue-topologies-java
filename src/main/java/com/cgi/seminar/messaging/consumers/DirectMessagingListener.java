package com.cgi.seminar.messaging.consumers;

import com.cgi.seminar.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class DirectMessagingListener {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @RabbitListener(queues = {"demo-direct-queue"}) // MessagingConfiguration.DIRECT_QUEUE_NAME
    void onMessageReceived(Message<Person> message) {
        Person person = message.getPayload();
        log.info("Received message from direct queue: {}", person);
    }
}
