package com.amparab.short_polling.controller;

import com.amparab.short_polling.service.ShortPollingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortPollingController {

    private final ShortPollingService shortPollingService;

    public ShortPollingController(ShortPollingService pollingService) {
        this.shortPollingService = pollingService;
    }

    @GetMapping("/poll")
    public Integer poll() {
        Integer res = shortPollingService.getData();
        System.out.println(res);
        return res;
    }
}

