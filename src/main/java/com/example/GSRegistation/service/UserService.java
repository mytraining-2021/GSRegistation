package com.example.GSRegistation.service;

import com.example.GSRegistation.model.RoleEntity;
import com.example.GSRegistation.model.UserEntitiy;
import com.example.GSRegistation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean isUsernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Transactional
    public UserEntitiy registerUser(String username, String password, String roleName) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username already exists");
        }

        RoleEntity role = new RoleEntity();
        role.setName(roleName);

        UserEntitiy user = new UserEntitiy();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); // Encode the password
        user.setRole(role);

        user.setAccountNumber(generateAccountNumber());

        return userRepository.save(user); // This will automatically save the associated role
    }

    private String generateAccountNumber() {
        Random random = new Random();
        String accountNumber;
        do {
            accountNumber = String.format("%06d", random.nextInt(1000000));
        } while (userRepository.existsByAccountNumber(accountNumber));
        return accountNumber;
    }

    public List<UserEntitiy> getAllUsers() {
        return userRepository.findAll();
    }
}
