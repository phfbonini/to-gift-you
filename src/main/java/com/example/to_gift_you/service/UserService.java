package com.example.to_gift_you.service;

import com.example.to_gift_you.domain.User;
import com.example.to_gift_you.dto.UserDTO;
import com.example.to_gift_you.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public String registerUser (UserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            return "Error: Username já está em uso!";
        }
        if(userRepository.existsByEmail(userDTO.getEmail())) {
            return "Error: Email já está em uso!";
        }

        User newUser = new User(
                null,
                userDTO.getUsername(),
                userDTO.getName(),
                userDTO.getEmail(),
                userDTO.getProfilePicture(),
                userDTO.getBiography(),
                LocalDate.now(),
                Boolean.TRUE,
                encoder.encode(userDTO.getPassword())
        );

        userRepository.save(newUser);

        return "Usuário cadastrado com sucesso";
    }

}
