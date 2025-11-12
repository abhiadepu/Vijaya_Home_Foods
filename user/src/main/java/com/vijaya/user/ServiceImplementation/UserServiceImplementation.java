package com.vijaya.user.ServiceImplementation;

import com.vijaya.user.Models.User;
import com.vijaya.user.payload.UserDTO;
import com.vijaya.user.payload.UserLoginDTO;
import com.vijaya.user.payload.UserRegisterDTO;
import com.vijaya.user.repositories.UserRepository;
import com.vijaya.user.services.UserService;
import com.vijaya.user.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private JwtUtil jwtService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String register(UserRegisterDTO dto) {
        System.out.println(dto.userName());
        User user = new User();
        user.setEmail(dto.email());
        user.setUserName(dto.userName());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setPhno(dto.phno());
        user.setAddress(dto.address());
        user.setCreatedAt();
        userRepository.save(user);
        return "Registration Successful";

    }

    @Override
    public String login(UserLoginDTO loginDTO) {
        System.out.println("loginnnn");
        System.out.println(loginDTO.email()+" "+loginDTO.password());
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.password()));
        System.out.println("loginnnn authhhh");
        System.out.println(auth);

        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        System.out.println(userDetails.getUsername());
        return jwtService.generateToken(userDetails.getUsername());
    }

    @Override
    public UserDTO getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("User Not Found"));
        return new UserDTO(user.getUserId(), user.getUserName(), user.getAddress(), user.getEmail(), user.getPhno());
    }

}
