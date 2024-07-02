package com.example.GSRegistation.model;

import com.example.GSRegistation.model.RoleEntity;

public class RegistrationResponse {

    private String username;
    private String accountNumber;
    private String role;

    public RegistrationResponse(String username, String accountNumber, RoleEntity role) {
        this.username = username;
        this.accountNumber = accountNumber;
        this.role = role.getName(); // Assuming you want the role name as a string
    }

    // Getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
