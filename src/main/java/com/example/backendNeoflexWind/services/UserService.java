package com.example.backendNeoflexWind.services;

import com.example.backendNeoflexWind.dto.SimpleUserDto;
import com.example.backendNeoflexWind.dto.UserDto;
import com.example.backendNeoflexWind.models.User;
import com.example.backendNeoflexWind.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserById(long id) {
        return userRepository.findById(id);
    }

    public Optional<User> addUser(UserDto userDto) {
        if (isUserWithSuchEmailExist(userDto.getEmail())) {
            return Optional.empty();
        }
        if (isUserWithSuchUsernameExist(userDto.getUsername())) {
            return Optional.empty();
        }
        User user = objectMapper.convertValue(userDto, User.class);
        return Optional.of(userRepository.save(user));
    }

    private boolean isUserWithSuchEmailExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private boolean isUserWithSuchUsernameExist(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public Optional<User> authenticate(SimpleUserDto userDto) {
        return userRepository.findByUsernameAndPassword(userDto.getUsername(), userDto.getPassword());
    }
}
