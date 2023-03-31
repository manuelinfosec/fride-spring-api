package com.cit305.fride.fride;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "Hello, World!";
    }
}
