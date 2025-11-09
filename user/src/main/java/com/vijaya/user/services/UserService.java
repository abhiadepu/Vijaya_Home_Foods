package com.vijaya.user.services;

import com.vijaya.user.Models.User;
import com.vijaya.user.payload.UserDTO;
import com.vijaya.user.payload.UserLoginDTO;
import com.vijaya.user.payload.UserRegisterDTO;

public interface UserService {

    public String register(UserRegisterDTO dto);
    public String login(UserLoginDTO loginDTO);
    public UserDTO getUser(Long id);
}
