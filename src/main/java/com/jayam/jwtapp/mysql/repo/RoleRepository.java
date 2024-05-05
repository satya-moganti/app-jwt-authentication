package com.jayam.jwtapp.mysql.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jayam.jwtapp.mysql.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}