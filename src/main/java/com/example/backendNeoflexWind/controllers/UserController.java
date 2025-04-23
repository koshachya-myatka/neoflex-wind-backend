package com.example.backendNeoflexWind.controllers;

import com.example.backendNeoflexWind.dto.SimpleUserDto;
import com.example.backendNeoflexWind.dto.UserDto;
import com.example.backendNeoflexWind.models.User;
import com.example.backendNeoflexWind.repositories.UserRepository;
import com.example.backendNeoflexWind.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userService.findUserById(id);
        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
        Optional<User> userOptional = userService.addUser(userDto);
        return userOptional.map(user -> ResponseEntity.status(201).body(user)).orElseGet(() -> ResponseEntity.status(400).build());
    }

    @PostMapping("/login")
    public ResponseEntity<User> authenticate(@RequestBody SimpleUserDto userDto) {
        Optional<User> userOptional = userService.authenticate(userDto);
        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}
