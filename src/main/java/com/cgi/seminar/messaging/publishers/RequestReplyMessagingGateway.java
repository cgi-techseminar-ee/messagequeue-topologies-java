package com.cgi.seminar.messaging.publishers;

import com.cgi.seminar.domain.Person;

public interface RequestReplyMessagingGateway {
    Object sendPersonAndReceiveReply(Person person);
}
