package com.artisticankit.paintingstore.service.impl;

import com.artisticankit.paintingstore.dto.AuthResponse;
import com.artisticankit.paintingstore.dto.LoginRequest;
import com.artisticankit.paintingstore.dto.RegisterRequest;
import com.artisticankit.paintingstore.entity.User;
import com.artisticankit.paintingstore.repository.UserRepository;
import com.artisticankit.paintingstore.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public AuthResponse registerUser(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email is already registered!");
        }

        String hashedPassword = BCrypt.hashpw(request.getPassword(), BCrypt.gensalt(12));

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(hashedPassword)
                .build();

        User savedUser = userRepository.save(user);

        return AuthResponse.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .message("User registered successfully!")
                .build();
    }

    @Override
    public AuthResponse loginUser(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found with this email!"));

        if (!BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password!");
        }

        return AuthResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .message("Login successful!")
                .build();
    }
}
