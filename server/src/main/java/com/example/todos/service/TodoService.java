package com.example.todos.service;

import com.example.todos.models.Todo;
import com.example.todos.models.User;
import com.example.todos.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoService {
    private final TodoRepository todoRepository;
    private final UserService userService;
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }
    public List<Todo> getTodosByUserId(Long userId) {
        return todoRepository.findByUserId(userId);
    }
    public Todo getTodoById(Long id) {
        return todoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
    }
    public Todo createTodo(Long userId, Todo data) {
        User user = userService.getUserById(userId);
        if (todoRepository.existsByUserIdAndTitle(userId, data.getTitle())) {
            throw new RuntimeException("Todo with this title already exists for this user");
        }
        data.setUser(user);
        return todoRepository.save(data);
    }
    public Todo updateTodo(Long id, Todo data) {
        Todo todo = getTodoById(id);
        todo.setTitle(data.getTitle());
        todo.setDescription(data.getDescription());
        todo.setCompleted(data.getCompleted());
        return todoRepository.save(todo);
    }
    public void deleteTodo(Long id) {
        Todo todo = getTodoById(id);
        todoRepository.delete(todo);
    }
}