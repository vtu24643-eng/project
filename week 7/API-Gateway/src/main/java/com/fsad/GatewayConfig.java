package com.fsad;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.cloud.gateway.server.mvc.predicate.GatewayRequestPredicates;
import org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class GatewayConfig {

    @Bean
    public RouterFunction<ServerResponse> userRoute() {
        return GatewayRouterFunctions.route("user-service")
            .route(GatewayRequestPredicates.path("/users/**"),
                HandlerFunctions.http())
            .filter(BeforeFilterFunctions.rewritePath("/users/(?<segment>.*)", "/${segment}"))
            .build();
    }
}