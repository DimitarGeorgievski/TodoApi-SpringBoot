package com.example.todos.service;

import com.example.todos.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    public User login(String email, String password) {
        return userService.getAllUsers().stream()
            .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Invalid credentials"));
    }
}
