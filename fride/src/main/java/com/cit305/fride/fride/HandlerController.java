package com.cit305.fride.fride;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cit305.fride.fride.structs.User;

@RestController
public class HandlerController {

    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> dashboard(@RequestBody Map<String, String> request) throws IOException {

        // collet request parameters
        String email = request.get("email");

        // collect every user from the database
        List<User> users = AuthController.getUsersList();

        // create a response placeholder
        Map<String, Object> response = new HashMap<>();

        // loop through all available users
        for (User user : users) {
            // if any user matches the email parameter
            if (user.getEmail().equals(email)) {
                // add user's firstname to response
                response.put("fistName", user.getFirstName());
                // add number of booked rides
                response.put("bookedRides", 12);

                // return response with 200 status code
                return ResponseEntity.ok(response);
            }
        }

        // if no user matches, return error that email wasn't found
        response.put("error", "Email not found");

        // return error response with 404 status code
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
