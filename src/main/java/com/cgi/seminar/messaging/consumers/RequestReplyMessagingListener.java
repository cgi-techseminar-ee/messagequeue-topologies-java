package com.cgi.seminar.messaging.consumers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.text.MessageFormat;

@Component
public class RequestReplyMessagingListener {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Inject
    protected RabbitTemplate messagingTemplate;

    @RabbitListener(queues = {"demo-reqrepl-queue"}) // MessagingConfiguration.REQUEST_REPLY_QUEUE_NAME
    void onMessageReceived(Message message) {
        String messageBody = new String(message.getBody());
        log.info("Received message from request/reply queue: {}", messageBody);
        String reply = MessageFormat.format("Reply to message -> {0}", messageBody);
        String replyRoutingKey = message.getMessageProperties().getReplyTo();
        log.info("Replying to routing key '{}' with message: '{}'", replyRoutingKey, reply);
        messagingTemplate.convertAndSend((String) replyRoutingKey, reply);
    }
}
