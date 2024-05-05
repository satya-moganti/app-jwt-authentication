package com.jayam.jwtapp.mysql.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jayam.jwtapp.mysql.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
