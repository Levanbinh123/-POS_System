package com.example.SaaS_POS_System.service;

import com.example.SaaS_POS_System.Exception.UserExcepion;
import com.example.SaaS_POS_System.payload.dto.UserDto;
import com.example.SaaS_POS_System.payload.response.AuthResponse;

public interface AuthService {
    AuthResponse register(UserDto userDto) throws Exception,UserExcepion;
    AuthResponse login(UserDto userDto) throws UserExcepion;
}
