package com.backend.backend.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.backend.user.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
}
