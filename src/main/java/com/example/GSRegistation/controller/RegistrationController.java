package com.example.GSRegistation.controller;

import com.example.GSRegistation.model.UserEntitiy;
import com.example.GSRegistation.model.RegistrationResponse;
import com.example.GSRegistation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping("/register/{role}")
    public ResponseEntity<?> registerUser(@PathVariable String role, @RequestBody UserEntitiy user) {
        if (userService.isUsernameExists(user.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        UserEntitiy savedUser = userService.registerUser(user.getUsername(), user.getPassword(), role.toUpperCase());

        Map<String, String> response = new HashMap<>();
        response.put("message", "Registration successful");
        response.put("accountNumber", savedUser.getAccountNumber());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/register/all")
    public ResponseEntity<List<RegistrationResponse>> getAllUsers() {
        List<UserEntitiy> users = userService.getAllUsers();
        List<RegistrationResponse> userResponseDTOs = users.stream()
                .map(user -> new RegistrationResponse(user.getUsername(), user.getAccountNumber(), user.getRole()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(userResponseDTOs);
    }
}