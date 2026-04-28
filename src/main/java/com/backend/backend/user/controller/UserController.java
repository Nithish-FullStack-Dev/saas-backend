package com.backend.backend.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend.common.response.ApiResponse;
import com.backend.backend.user.dto.LoginRequest;
import com.backend.backend.user.dto.RegisterRequest;
import com.backend.backend.user.entity.User;
import com.backend.backend.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> register(@RequestBody RegisterRequest registerRequest) {

        User user = userService.register(registerRequest);
        ApiResponse<User> apiResponse = new ApiResponse<User>(true, "User registered successfully", user);

        return ResponseEntity.status(201).body(apiResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<User>> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.login(loginRequest);

        ApiResponse<User> apiResponse = new ApiResponse<User>(true, "User Logged in Successfully", user);

        return ResponseEntity.ok(apiResponse);

    }

    // public ResponseEntity<ApiResponse<List<User>>> getAllUsers(){
    // List<User> list = userService.getAllUsers();
    // ApiResponse<List<User>> apiResponse = new ApiResponse<>(true, "Users fetched
    // successfully", list);
    // return ResponseEntity.ok(list);
    // }

}
