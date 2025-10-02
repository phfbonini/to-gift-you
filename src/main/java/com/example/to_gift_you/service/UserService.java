package com.example.to_gift_you.service;

import com.example.to_gift_you.domain.User;
import com.example.to_gift_you.dto.UserDTO;
import com.example.to_gift_you.exception.user.InvalidPasswordException;
import com.example.to_gift_you.exception.user.UserAlreadyExistsException;
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

    private static final int TAMANHO_MINIMO_SENHA = 8;

    public String registerUser(UserDTO userDTO) {
        validateUserDto(userDTO);

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

    private void validateUserDto(UserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new UserAlreadyExistsException("Username já está em uso!");
        }
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new UserAlreadyExistsException("Email já está em uso!");
        }
        if (userDTO.getPassword().length() < TAMANHO_MINIMO_SENHA) {
            throw new InvalidPasswordException("Sua senha precisa ter pelo menos 8 caracteres");
        }
    }
}

