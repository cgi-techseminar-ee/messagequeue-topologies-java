package com.cgi.seminar.messaging.publishers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class MessagingGatewayBase {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Inject
    protected RabbitTemplate messagingTemplate;
}
