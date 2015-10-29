package com.cgi.seminar;

import com.cgi.seminar.controller.MessagingController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.inject.Inject;

@SpringBootApplication
public class MqDemoApplication implements CommandLineRunner {

    @Inject
    private MessagingController controller;

    public static void main(String[] args) {
        SpringApplication.run(MqDemoApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        controller
                .publishMessageToDirect()
                .publishMessageToPubSub()
                .publishMessageToRequestReply();

        // listeners are activated automatically by @Component
    }
}
