package com.example.todos.service;

import com.example.todos.models.User;
import com.example.todos.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User getUserById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("User with this email already exists");
        }
        return userRepository.save(user);
    }
    public User updateUser(Long id, User data) {
        User user = getUserById(id);
        if (!user.getEmail().equals(data.getEmail()) &&
            userRepository.existsByEmail(data.getEmail())) {
            throw new RuntimeException("User with this email already exists");
        }
        BeanUtils.copyProperties(data, user, "id");
        return userRepository.save(user);
    }
    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }
}