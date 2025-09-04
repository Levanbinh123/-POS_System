package com.example.SaaS_POS_System.modal.mapper;

import com.example.SaaS_POS_System.modal.User;
import com.example.SaaS_POS_System.payload.dto.UserDto;


public class UserMapper {
    public static UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFullName(user.getFullName());
        userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setUpdatedAt(user.getUpdatedAt());
        userDto.setLastLogin(user.getLastLogin());
        userDto.setPhone(user.getPhone());
        return userDto;

    }
}
