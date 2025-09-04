package com.example.SaaS_POS_System.repository;

import com.example.SaaS_POS_System.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
}
