package com.fsad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JMSController {

    @Autowired
    MessageSender messageSender;

    @GetMapping("/send")
    public String sendMessage(@RequestParam String message) {
        messageSender.send(message);
        return "Message sent: " + message;
    }
}