package com.backend.backend.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend.common.response.ApiResponse;
import com.backend.backend.user.dto.RegisterRequest;
import com.backend.backend.user.entity.User;
import com.backend.backend.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> register(RegisterRequest registerRequest) {

        User user = userService.register(registerRequest);
        ApiResponse<User> apiResponse = new ApiResponse<User>(true, "User registered successfully", user);

        return ResponseEntity.status(201).body(apiResponse);
    }

}
