package com.fsad;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("user-service", r -> r
                .path("/users/**")
                .uri("lb://USER-MS"))
            .route("account-service", r -> r
                .path("/accounts/**")
                .uri("lb://ACCOUNTMNG-MS"))
            .build();
    }
}