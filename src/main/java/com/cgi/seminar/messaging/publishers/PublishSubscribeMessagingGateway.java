package com.cgi.seminar.messaging.publishers;

import com.cgi.seminar.domain.Person;

public interface PublishSubscribeMessagingGateway {
    void sendPerson(Person person);
}
