package com.cgi.seminar.messaging.publishers;

import com.cgi.seminar.domain.Person;
import org.springframework.stereotype.Component;

import static com.cgi.seminar.messaging.MessagingConfiguration.PUBLISH_SUBSCRIBE_EXCHANGE_NAME;

@Component
public class PublishSubscribeMessagingGatewayImpl extends MessagingGatewayBase implements PublishSubscribeMessagingGateway {

    @Override
    public void sendPerson(Person person) {
        messagingTemplate.convertAndSend(PUBLISH_SUBSCRIBE_EXCHANGE_NAME, null, person, message -> {
            log.info("Sending person message to publish/subscribe exchange: {}", new String(message.getBody()));
            return message;
        });
    }
}
