package com.amparab.longpolling.long_polling.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RestController
public class LongPollingController {

    private String latestMessage = null;

    @GetMapping("/poll")
    public DeferredResult<String> poll() {
        System.out.println("Received a request");

        // DeferredResult allows Spring to hold the request open without blocking a thread.
        DeferredResult<String> deferredResult = new DeferredResult<>(30000L, "No new data");

        // Periodically check for new data
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            if (latestMessage != null) {
                deferredResult.setResult(latestMessage);
                latestMessage = null;
            }
        }, 0, 500, TimeUnit.MILLISECONDS);

        return deferredResult;
    }

    // Simulate message creation
    @PostMapping("/send")
    public String sendMessage(@RequestBody String message) {
        latestMessage = message;
        return "Message received";
    }
}
