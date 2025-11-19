package com.example.todos.controller;

import com.example.todos.models.Todo;
import com.example.todos.service.TodoService;
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
@RequestMapping("/api/todos")
@RequiredArgsConstructor
@Tag(name = "Todos", description = "API for managing todos")
public class TodoController {
    private final TodoService todoService;
    @Operation(summary = "Get all todos", description = "Returns a list of all todos")
    @ApiResponse(responseCode = "200", description = "Successfully fetched todos")
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        return ResponseEntity.ok(todoService.getAllTodos());
    }
    @Operation(summary = "Get todos by user ID", description = "Returns all todos for a specific user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully fetched todos"),
        @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Todo>> getTodosByUserId(
            @Parameter(description = "ID of the user") @PathVariable Long userId) {
        return ResponseEntity.ok(todoService.getTodosByUserId(userId));
    }
    @Operation(summary = "Get todo by ID", description = "Returns a todo by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully fetched todo"),
        @ApiResponse(responseCode = "404", description = "Todo not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(
            @Parameter(description = "ID of the todo") @PathVariable Long id) {
        return ResponseEntity.ok(todoService.getTodoById(id));
    }
    @Operation(summary = "Create a new todo", description = "Creates a new todo for a user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Todo successfully created"),
        @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content)
    })
    @PostMapping("/user/{userId}")
    public ResponseEntity<Todo> createTodo(
            @Parameter(description = "ID of the user") @PathVariable Long userId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Todo data to create",
                required = true,
                content = @Content(schema = @Schema(implementation = Todo.class))
            )
            @Valid @RequestBody Todo todo) {
        return new ResponseEntity<>(todoService.createTodo(userId, todo), HttpStatus.CREATED);
    }
    @Operation(summary = "Update a todo", description = "Updates an existing todo by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Todo successfully updated"),
        @ApiResponse(responseCode = "404", description = "Todo not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(
            @Parameter(description = "ID of the todo") @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Updated todo data",
                required = true,
                content = @Content(schema = @Schema(implementation = Todo.class))
            )
            @Valid @RequestBody Todo todo) {
        return ResponseEntity.ok(todoService.updateTodo(id, todo));
    }
    @Operation(summary = "Delete a todo", description = "Deletes a todo by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Todo successfully deleted"),
        @ApiResponse(responseCode = "404", description = "Todo not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(
            @Parameter(description = "ID of the todo") @PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
}