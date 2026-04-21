package com.fsad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AccountMngMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountMngMsApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

@RestController
class AccountController {

    private final RestTemplate restTemplate;

    public AccountController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/accounts")
    public String getAccounts() {
        // Calling User-MS through Eureka using service name, not hardcoded port
        String userResponse = restTemplate.getForObject("http://USER-MS/users", String.class);
        return "AccountMng-MS received from User-MS: " + userResponse;
    }
}