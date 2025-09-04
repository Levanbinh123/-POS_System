package com.example.SaaS_POS_System.controller;

import com.example.SaaS_POS_System.Exception.UserExcepion;
import com.example.SaaS_POS_System.modal.User;
import com.example.SaaS_POS_System.payload.dto.UserDto;
import com.example.SaaS_POS_System.payload.response.AuthResponse;
import com.example.SaaS_POS_System.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> signup(@RequestBody UserDto userDto) throws Exception, UserExcepion {
        return ResponseEntity.ok(authService.register(userDto));

    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserDto userDto) throws UserExcepion {
        return ResponseEntity.ok(authService.login(userDto));
    }
}
