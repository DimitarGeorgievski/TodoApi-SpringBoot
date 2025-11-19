package com.example.todos.controller;

import com.example.todos.models.User;
import com.example.todos.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "API for users")
public class UserController {
    private final UserService userService;
    @Operation(summary = "get all users", description = "returns all users")
    @ApiResponse(responseCode = "200", description = "successfully fetching users")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @Operation(summary = "get user by ID", description = "return one user with ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "found user"),
        @ApiResponse(responseCode = "400", description = "user not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(
            @Parameter(description = "user id") @PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @Operation(summary = "create new user", description = "create new user with email and username")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "successfully created user"),
        @ApiResponse(responseCode = "400", description = "invalid credentials", content = @Content)
    })
    @PostMapping
    public ResponseEntity<User> createUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "data for new user",
                required = true,
                content = @Content(schema = @Schema(implementation = User.class))
            )
            @Valid @RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }
    @Operation(summary = "update user", description = "update user with data")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "user successfully updated"),
        @ApiResponse(responseCode = "400", description = "invalid credentials or data", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @Parameter(description = "user ID") @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "updated data for user",
                required = true
            )
            @Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }
    @Operation(summary = "delete user", description = "Delete users and it's todos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "User successfully deleted"),
        @ApiResponse(responseCode = "400", description = "User not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "User ID") @PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}