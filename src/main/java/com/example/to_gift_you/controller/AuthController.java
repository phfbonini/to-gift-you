package com.example.to_gift_you.controller;


import com.example.to_gift_you.domain.User;
import com.example.to_gift_you.repository.UserRepository;
import com.example.to_gift_you.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtil jwtUtils;

    @PostMapping("/signin")
    public String authenticateUser(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtUtils.generateToken(userDetails.getUsername());
    }
    @PostMapping("/signup")
    public String registerUser(@RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return "Error: Username is already taken!";
        }
        User newUser = new User(
                null,
                user.getUsername(),
                encoder.encode(user.getPassword())
        );
        userRepository.save(newUser);
        return "User registered successfully!";
    }
}
