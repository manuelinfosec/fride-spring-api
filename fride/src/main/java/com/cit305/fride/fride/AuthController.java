package com.cit305.fride.fride;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> request) throws IOException {
        String email = request.get("email");
        String password = request.get("password");

        // TODO: validate the email and password, and save them to a JSON file
        // Thinking of using Jackson library to read and write JSON files

        return "Login successful!";
    }

    @PostMapping("/register")
    public String register(@RequestBody Map<String, String> request) throws IOException {
        String firstName = request.get("firstname");
        String lastName = request.get("lastname");
        String email = request.get("email");
        String gender = request.get("gender");
        String password = request.get("password");

        // TODO: read the existing users from the JSON file, add the new user, and write
        // them back to the file

        return "Registration Successful";
    }

}
