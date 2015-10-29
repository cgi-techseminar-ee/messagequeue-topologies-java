package com.cgi.seminar.messaging;

public interface MessagingConfiguration {
    public static final String DIRECT_EXCHANGE_NAME = "demo-direct-exchange";
    public static final String DIRECT_QUEUE_NAME = "demo-direct-queue";
    public static final String DIRECT_ROUTINGKEY_NAME = "demo-direct-routingkey";

    public static final String PUBLISH_SUBSCRIBE_EXCHANGE_NAME = "demo-pubsub-exchange";
    public static final String PUBLISH_SUBSCRIBE_QUEUE1_NAME = "demo-pubsub-queue1";
    public static final String PUBLISH_SUBSCRIBE_QUEUE2_NAME = "demo-pubsub-queue2";

    public static final String REQUEST_REPLY_EXCHANGE_NAME = "demo-reqrepl-exchange";
    public static final String REQUEST_REPLY_QUEUE_NAME = "demo-reqrepl-queue";
    public static final String REQUEST_REPLY_ROUTINGKEY_NAME = "demo-reqrepl-routingkey";

}
