package com.example.LionBracketCreator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("/api/v1/test")
    public String test() {
        return "This is a test";
    }
}
