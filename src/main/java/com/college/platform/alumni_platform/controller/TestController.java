package com.college.platform.alumni_platform.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test/secure")
    public String secureApi() {
        return "JWT is working! You are authenticated.";
    }
}