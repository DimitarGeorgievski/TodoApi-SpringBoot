package com.example.todos.controller;

import com.example.todos.models.User;
import com.example.todos.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Authentication API")
public class AuthController {
    private final AuthService authService;
    @Operation(summary = "Login", description = "Authenticate user with email and password")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully logged in"),
        @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestParam String email,@RequestParam String password) {
        User user = authService.login(email, password);
        return ResponseEntity.ok(user);
    }
}
