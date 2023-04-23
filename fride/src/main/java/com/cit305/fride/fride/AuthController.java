package com.cit305.fride.fride;

import com.cit305.fride.fride.structs.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    // path to database file: JSON
    private static final String USERS_JSON_FILE = "users.json";

    // initialize an object mapper
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<User> getUsersList() throws IOException {
        // load file to memory
        File file = new File(USERS_JSON_FILE);

        // check if file exists
        if (!file.exists()) {
            return new ArrayList<>();
        }

        // return a list of user objects as collected from the database
        return objectMapper.readValue(file, new TypeReference<List<User>>() {
        });
    }


    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody Map<String, String> request) throws IOException {
        
        // collect login parameters
        String email = request.get("email");
        String password = request.get("password");

        // collect every user from the database
        List<User> users = getUsersList();

        // loop through all available users
        for (User user : users) {
            // if any user matches the login parameters
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                // return details about the user
                return ResponseEntity.ok(user);
            }
        }
        // send back a response that the request user was not found
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody Map<String, String> request) throws IOException {

        // collect request parameters
        String firstName = request.get("firstname");
        String lastName = request.get("lastname");
        String email = request.get("email");
        String phone = request.get("phone");
        String password = request.get("password");

        List<User> users = getUsersList();

        // Check if email is alread registered
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }

        // Create a new user
        User newUser = new User(firstName, lastName, email, phone, password);

        // Add new user to the list
        users.add(newUser);

        // Write updated list back to database
        objectMapper.writeValue(new File(USERS_JSON_FILE), users);
        
        // return that details about new user
        return ResponseEntity.ok(newUser);
    }

}
