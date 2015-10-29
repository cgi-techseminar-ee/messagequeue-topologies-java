package com.cgi.seminar.controller;

public interface MessagingController {
    MessagingController publishMessageToDirect();

    MessagingController publishMessageToPubSub();

    MessagingController publishMessageToRequestReply();
}
