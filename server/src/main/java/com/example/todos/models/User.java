package com.example.todos.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "firstName is required")
    @Size(min = 2, max = 50)
    @Column(nullable = false)
    private String firstName;
    @NotBlank(message = "lastName is required")
    @Size(min = 2, max = 50)
    @Column(nullable = false)
    private String lastName;
    @NotBlank(message = "Email is required")
    @Email
    @Column(nullable = false, unique = true)
    private String email;
    @NotBlank(message = "password is required")
    @Size(min = 6, max = 100)
    @Column(nullable = false)
    private String password;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Todo> todos = new ArrayList<>();
    public void addTodo(Todo todo) {
        todos.add(todo);
        todo.setUser(this);
    }
    public void removeTodo(Todo todo) {
        todos.remove(todo);
        todo.setUser(null);
    }
}