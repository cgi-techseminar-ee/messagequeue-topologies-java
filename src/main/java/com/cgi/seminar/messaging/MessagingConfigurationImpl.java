package com.cgi.seminar.messaging;

import com.cgi.seminar.messaging.publishers.DirectMessagingGateway;
import com.cgi.seminar.messaging.publishers.PublishSubscribeMessagingGateway;
import com.cgi.seminar.messaging.publishers.RequestReplyMessagingGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Component
public class MessagingConfigurationImpl implements MessagingConfiguration {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Inject
    private AmqpAdmin amqpAdmin;

    @Inject
    private RabbitTemplate messagingTemplate;

    @Inject
    private SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory;

    @Inject
    private DirectMessagingGateway directMessagingGateway;

    @Inject
    private PublishSubscribeMessagingGateway publishSubscribeMessagingGateway;

    @Inject
    private RequestReplyMessagingGateway requestReplyMessagingGateway;

    @PostConstruct
    public void init() {
        log.info("Configuring messaging");

        messagingTemplate.setMessageConverter(new JsonMessageConverter());
        rabbitListenerContainerFactory.setMessageConverter(new JsonMessageConverter());

        setupDirectExchangesAndQueues();
        setupPublishSubscribeExchangesAndQueues();
        setupRequestReplyExchangesAndQueues();
    }

    private void setupDirectExchangesAndQueues() {
        final DirectExchange exchange = new DirectExchange(DIRECT_EXCHANGE_NAME);
        final Queue queue = new Queue(DIRECT_QUEUE_NAME, true);
        final Binding binding = BindingBuilder.bind(queue).to(exchange).with(DIRECT_ROUTINGKEY_NAME);

        amqpAdmin.declareExchange(exchange);
        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareBinding(binding);
    }

    private void setupPublishSubscribeExchangesAndQueues() {
        final FanoutExchange exchange = new FanoutExchange(PUBLISH_SUBSCRIBE_EXCHANGE_NAME);
        final Queue queue1 = new Queue(PUBLISH_SUBSCRIBE_QUEUE1_NAME, true);
        final Queue queue2 = new Queue(PUBLISH_SUBSCRIBE_QUEUE2_NAME, true);
        final Binding binding1 = BindingBuilder.bind(queue1).to(exchange);
        final Binding binding2 = BindingBuilder.bind(queue2).to(exchange);

        amqpAdmin.declareExchange(exchange);
        amqpAdmin.declareQueue(queue1);
        amqpAdmin.declareQueue(queue2);
        amqpAdmin.declareBinding(binding1);
        amqpAdmin.declareBinding(binding2);
    }

    private void setupRequestReplyExchangesAndQueues() {
        final DirectExchange exchange = new DirectExchange(REQUEST_REPLY_EXCHANGE_NAME);
        final Queue queue = new Queue(REQUEST_REPLY_QUEUE_NAME, true);
        final Binding binding = BindingBuilder.bind(queue).to(exchange).with(REQUEST_REPLY_ROUTINGKEY_NAME);

        amqpAdmin.declareExchange(exchange);
        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareBinding(binding);
    }
}
