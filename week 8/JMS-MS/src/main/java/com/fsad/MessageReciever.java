package com.fsad;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReciever {

    @JmsListener(destination = "myQueue")
    public void receive(String message) {
        System.out.println("Received: " + message);
    }
}