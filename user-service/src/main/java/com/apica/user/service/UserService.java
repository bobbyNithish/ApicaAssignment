package com.apica.user.service;

import com.apica.user.kafka.UserEventProducer;
import com.apica.user.model.User;
import com.apica.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserEventProducer userEventProducer;

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);

        // Create a simple JSON event message
        String event = String.format(
            "{\"eventType\":\"USER_CREATED\", \"userId\":%d, \"timestamp\":\"%s\"}",
            savedUser.getId(),
            java.time.Instant.now().toString()
        );

        userEventProducer.sendUserEvent(event);
        return savedUser;
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get user by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Update user
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(updatedUser.getUsername());
                    user.setEmail(updatedUser.getEmail());
                    user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                    user.setRole(updatedUser.getRole());
                    User savedUser = userRepository.save(user);
                    userEventProducer.sendUserEvent("User updated: " + savedUser.getUsername());
                    return savedUser;
                })
                .orElse(null);
    }

    // Delete user
    public void deleteUser(Long id) {
        userRepository.findById(id).ifPresent(user -> {
            userRepository.deleteById(id);
            userEventProducer.sendUserEvent("User deleted: " + user.getUsername());
        });
    }
}
