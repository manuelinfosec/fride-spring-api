package com.cit305.fride.fride;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cit305.fride.fride.structs.User;

@RestController
public class HandlerController {

    @GetMapping("/dashboard")
    public String dashboard(@RequestBody Map<String, String> request) {

        try {
            String email = request.get("email");

            List<User> users = AuthController.getUsersList();

            JSONObject response = new JSONObject();

            for (User user : users) {
                if (user.getEmail().equals(email)) {
                    response.put("fistName", user.getFirstName());
                    response.put("bookedRides", 12);

                    return response.toString();
                }
            }

            response.put("error", "Email not found");
            return response.toString();

        } catch (JSONException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
