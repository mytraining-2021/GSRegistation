package com.example.GSRegistation.controller;

import com.example.GSRegistation.jwt.JwtUtil;
import com.example.GSRegistation.model.LoginRequest;
import com.example.GSRegistation.model.LoginResponse;
import com.example.GSRegistation.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest authRequest) {
        try {
            // Check if the user exists in the database
            UserDetails userDetails;
            try {
                userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
            } catch (UsernameNotFoundException e) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "User not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            // Authenticate the user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            // Generate JWT token
            final String jwt = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(new LoginResponse(jwt));
        } catch (BadCredentialsException e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
