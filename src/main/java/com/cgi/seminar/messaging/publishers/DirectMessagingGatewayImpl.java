package com.cgi.seminar.messaging.publishers;

import com.cgi.seminar.domain.Person;
import org.springframework.stereotype.Component;

import static com.cgi.seminar.messaging.MessagingConfiguration.DIRECT_EXCHANGE_NAME;
import static com.cgi.seminar.messaging.MessagingConfiguration.DIRECT_ROUTINGKEY_NAME;

@Component
public class DirectMessagingGatewayImpl extends MessagingGatewayBase implements DirectMessagingGateway {

    @Override
    public void sendPerson(Person person) {
        messagingTemplate.convertAndSend(DIRECT_EXCHANGE_NAME, DIRECT_ROUTINGKEY_NAME, person, message -> {
            log.info("Sending person message to direct exchange: {}", new String(message.getBody()));
            return message;
        });
    }
}
