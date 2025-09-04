package com.example.SaaS_POS_System.service.impl;

import com.example.SaaS_POS_System.Exception.UserExcepion;
import com.example.SaaS_POS_System.configuration.JwtProvider;
import com.example.SaaS_POS_System.modal.User;
import com.example.SaaS_POS_System.modal.enum_.UserRole;
import com.example.SaaS_POS_System.modal.mapper.UserMapper;
import com.example.SaaS_POS_System.payload.dto.UserDto;
import com.example.SaaS_POS_System.payload.response.AuthResponse;
import com.example.SaaS_POS_System.repository.UserRepository;
import com.example.SaaS_POS_System.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Collection;
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    public AuthResponse register(UserDto userDto) throws Exception,UserExcepion {
        User user=userRepository.findByEmail(userDto.getEmail());
        if(user!=null){
            throw  new UserExcepion("Email Already Exists");
        }
        if(userDto.getRole().equals(UserRole.ROLE_ADMIN)){
            throw  new UserExcepion("Admin Role is not allowed");
        }
        try {
            User newUser=new User();
            newUser.setEmail(userDto.getEmail());
            newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
            newUser.setRole(userDto.getRole() != null ? userDto.getRole() : UserRole.ROLE_USER);
            newUser.setFullName(userDto.getFullName());
            newUser.setPhone(userDto.getPhone());
            newUser.setLastLogin(LocalDateTime.now());
            newUser.setCreatedAt(LocalDateTime.now());
            newUser.setUpdatedAt(LocalDateTime.now());
            User savedUser=userRepository.save(newUser);
            Authentication authentication = authenticate(userDto.getEmail(), userDto.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt= JwtProvider.generateToken(authentication);
            AuthResponse authResponse=new AuthResponse();
            authResponse.setToken(jwt);
            authResponse.setMessage("Success");
            authResponse.setUserDto(UserMapper.toDto(savedUser));
            return authResponse;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }



    }
    @Override
    public AuthResponse login(UserDto userDto) throws UserExcepion {
        String email=userDto.getEmail();
        String password=userDto.getPassword();
        Authentication  authentication=authenticate(userDto.getEmail(), userDto.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt=JwtProvider.generateToken(authentication);
        User user=userRepository.findByEmail(email);
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);
        AuthResponse authResponse=new AuthResponse();
        authResponse.setToken(jwt);
        authResponse.setMessage("login Success");
        authResponse.setUserDto(UserMapper.toDto(user));
        return authResponse;
    }

    private Authentication authenticate(String email, String password) throws UserExcepion {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        if (userDetails == null) {
            throw new UserExcepion("Email is do no Exists");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new UserExcepion("Wrong password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
