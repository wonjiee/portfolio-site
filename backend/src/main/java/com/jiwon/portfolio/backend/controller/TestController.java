package com.jiwon.portfolio.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String home() {
        return "Portfolio Backend Running";
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}