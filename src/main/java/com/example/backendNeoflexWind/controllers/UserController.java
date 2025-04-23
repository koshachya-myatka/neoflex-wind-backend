package com.example.backendNeoflexWind.controllers;

import com.example.backendNeoflexWind.dto.SimpleUserDto;
import com.example.backendNeoflexWind.dto.UserDto;
import com.example.backendNeoflexWind.models.User;
import com.example.backendNeoflexWind.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public User createUser(@RequestBody UserDto userDto) {
        User user = objectMapper.convertValue(userDto, User.class);
        return userRepository.save(user);
    }

    @PostMapping("/login")
    public ResponseEntity<User> authenticate(@RequestBody SimpleUserDto userDto) {
        Optional<User> userOptional = userRepository.findByUsernameAndPassword(userDto.getUsername(), userDto.getPassword());
        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
