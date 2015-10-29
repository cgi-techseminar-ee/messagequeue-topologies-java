package com.cgi.seminar.controller;

import com.cgi.seminar.domain.Person;
import com.cgi.seminar.messaging.publishers.DirectMessagingGateway;
import com.cgi.seminar.messaging.publishers.PublishSubscribeMessagingGateway;
import com.cgi.seminar.messaging.publishers.RequestReplyMessagingGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Random;

@Component
public class MessagingControllerImpl implements MessagingController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final Random random = new Random();

    @Inject
    DirectMessagingGateway directMessagingGateway;

    @Inject
    PublishSubscribeMessagingGateway publishSubscribeMessagingGateway;

    @Inject
    RequestReplyMessagingGateway requestReplyMessagingGateway;

    @Override
    public MessagingController publishMessageToDirect() {
        log.info("Publishing direct message");
        directMessagingGateway.sendPerson(makePerson("Mr Direct"));
        return this;
    }

    @Override
    public MessagingController publishMessageToPubSub() {
        log.info("Publishing publish-subscribe message");
        publishSubscribeMessagingGateway.sendPerson(makePerson("Mr Publish/Subscribe"));
        return this;
    }

    @Override
    public MessagingController publishMessageToRequestReply() {
        log.info("Sending request message");
        Object reply = requestReplyMessagingGateway.sendPersonAndReceiveReply(makePerson("Mr Request/Reply"));
        log.info("Received reply message: {}", reply);
        return this;
    }

    private Person makePerson(String name) {
        Person person = new Person();
        person.setName(name);
        person.setId(random.nextInt(100));
        return person;
    }
}
