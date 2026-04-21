package com.fsad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageSender {

    @Autowired
    JmsTemplate jmsTemplate;

    public void send(String message) {
        jmsTemplate.convertAndSend("myQueue", message);
        System.out.println("Sent: " + message);
    }
}
