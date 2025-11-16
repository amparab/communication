package com.amparab.short_polling.service;

import org.springframework.stereotype.Service;
@Service
public class ShortPollingService {

    private static int counter = 0;

    public ShortPollingService() {
        Thread t = new Thread(() -> {
            while (true) {
                counter++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t.start();
    }

    public Integer getData() {
        return counter;
    }
}
