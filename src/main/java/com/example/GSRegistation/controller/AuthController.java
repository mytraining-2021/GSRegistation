package com.example.GSRegistation.controller;

import com.example.GSRegistation.model.RoleEntity;
import com.example.GSRegistation.service.CustomUserDetails;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AuthController {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/auth")
    public ResponseEntity<?> protectedEndpoint() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        String accountNumber = userDetails.getAccountNumber();
        String roleName = userDetails.getRole().getName();

        // Create response map
        Map<String, Object> response = new HashMap<>();
        response.put("username", username);
        response.put("accountNumber", accountNumber);
        response.put("role", roleName);

        return ResponseEntity.ok(response);
    }
}
