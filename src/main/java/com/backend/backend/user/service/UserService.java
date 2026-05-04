package com.backend.backend.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend.auth.jwt.JwtUtil;
import com.backend.backend.common.exception.ResourceAlreadyExistsException;
import com.backend.backend.common.exception.ResourceNotFoundException;
import com.backend.backend.common.exception.UnauthorizedException;
import com.backend.backend.user.dto.LoginRequest;
import com.backend.backend.user.dto.RegisterRequest;
import com.backend.backend.user.entity.User;
import com.backend.backend.user.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public User register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException("Email already exists");
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhone());
        user.setRole("USER");
        user.setStatus("ACTIVE");

        userRepository.save(user);

        return user;
    }

    public String login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());

        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }

        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new UnauthorizedException("Invalid password");
        }

        return jwtUtil.generateToken(user.getEmail());
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
