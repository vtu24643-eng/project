package com.fsad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class UserMsApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserMsApplication.class, args);
    }
}

@RestController
class UserController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/users")
    public String getUsers() {
        return "Hello from User-MS on port: " + port;
    }
}