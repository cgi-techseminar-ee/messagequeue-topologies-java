package com.cgi.seminar.messaging.publishers;

import com.cgi.seminar.domain.Person;

public interface DirectMessagingGateway {
    void sendPerson(Person person);
}
