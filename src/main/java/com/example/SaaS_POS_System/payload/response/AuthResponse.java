package com.example.SaaS_POS_System.payload.response;

import com.example.SaaS_POS_System.payload.dto.UserDto;
import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String message;
    private UserDto userDto;
}
