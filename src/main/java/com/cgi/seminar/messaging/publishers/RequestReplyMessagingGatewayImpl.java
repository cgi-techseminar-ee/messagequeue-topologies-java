package com.cgi.seminar.messaging.publishers;

import com.cgi.seminar.domain.Person;
import org.springframework.stereotype.Component;

import static com.cgi.seminar.messaging.MessagingConfiguration.REQUEST_REPLY_EXCHANGE_NAME;
import static com.cgi.seminar.messaging.MessagingConfiguration.REQUEST_REPLY_ROUTINGKEY_NAME;

@Component
public class RequestReplyMessagingGatewayImpl extends MessagingGatewayBase implements RequestReplyMessagingGateway {

    @Override
    public Object sendPersonAndReceiveReply(Person person) {
        return messagingTemplate.convertSendAndReceive(REQUEST_REPLY_EXCHANGE_NAME, REQUEST_REPLY_ROUTINGKEY_NAME, person, message -> {
            log.info("Sending person message to request/reply exchange: {}", new String(message.getBody()));
            return message;
        });
    }

}
