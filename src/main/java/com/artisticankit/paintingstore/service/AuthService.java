package com.artisticankit.paintingstore.service;

import com.artisticankit.paintingstore.dto.AuthResponse;
import com.artisticankit.paintingstore.dto.LoginRequest;
import com.artisticankit.paintingstore.dto.RegisterRequest;

public interface AuthService {
    AuthResponse registerUser(RegisterRequest request);
    AuthResponse loginUser(LoginRequest request);
}
