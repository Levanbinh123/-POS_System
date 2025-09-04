package com.example.SaaS_POS_System.payload.dto;

import com.example.SaaS_POS_System.modal.enum_.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class UserDto {
    private long id;
    private String fullName;
    private String email;
    private String phone;
    private String password;
    private UserRole role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLogin;
}
