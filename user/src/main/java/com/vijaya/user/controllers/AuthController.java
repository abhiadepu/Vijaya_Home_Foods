package com.vijaya.user.controllers;

import com.vijaya.user.Models.User;
import com.vijaya.user.payload.UserDTO;
import com.vijaya.user.payload.UserLoginDTO;
import com.vijaya.user.payload.UserRegisterDTO;
import com.vijaya.user.services.UserService;
import com.vijaya.user.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/login")
    public String login(@RequestBody UserLoginDTO loginDTO){
        return userService.login(loginDTO);
    }

    @PostMapping("/signup")
    public String signup(@RequestBody UserRegisterDTO dto){
        return userService.register(dto);
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @GetMapping("/extract-userid")
    public Long extractUserId(@RequestHeader("Authorization") String token) {
        return jwtUtil.extractUserIdFromToken(token);
    }

}
