package com.amparab.communication.websocket.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {

    /**
     * Registers all classes annotated with @ServerEndpoint to the underlying
     * WebSocket runtime (Tomcat/Jetty/Undertow).
     *
     * Without this bean, Spring Boot won't automatically detect and expose
     * @ServerEndpoint endpoints.
     */

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}