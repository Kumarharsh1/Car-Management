package com.example.parkinglot.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    
    @GetMapping("/test/hello")
    public String hello() {
        return "Test controller is working!";
    }
    
    @PostMapping("/test/echo")
    public String echo(@RequestBody String text) {
        return "Echo: " + text;
    }
}
