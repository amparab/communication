package com.amparab.communication.websocket.websocket;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * A simple WebSocket endpoint exposed at /chat for handling chat connections.
 *
 * Note:
 * - @ServerEndpoint registers it with the WebSocket runtime.
 * - @Component ensures it is a Spring bean.
 */
@Slf4j
@ServerEndpoint("/chat")
@Component
public class ChatEndpoint {

    /**
     * Called when a new WebSocket connection is established.
     */
    @OnOpen
    public void onOpen(Session session) {
        log.info("Connection established!");
        try {
            session.getBasicRemote().sendText("Welcome! What is your name?");
        } catch (Exception e) {
            log.error("An error has occurred!");
        }
    }

    /**
     * Called when the server receives a message from the client.
     */
    @OnMessage
    public String onMessage(Session session, String message) {
        log.info("Received a message from client : {}", message);
        return "Hello " + message + "!";
    }

    /**
     * Called when the connection is closed.
     */
    @OnClose
    public void onClose(Session session) {
        log.info("Connection closed!");
    }

    /**
     * Called when an error occurs during the WebSocket communication.
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error("Some error occurred. Handle me!");
    }
}
