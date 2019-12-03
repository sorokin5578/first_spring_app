package com.example.lab_4sprg.repos;

import com.example.lab_4sprg.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
