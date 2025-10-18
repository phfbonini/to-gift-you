package com.example.to_gift_you.service;

import com.example.to_gift_you.domain.User;
import com.example.to_gift_you.dto.RegisterRequestDTO;
import com.example.to_gift_you.dto.RegisterResponseDTO;
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

    public RegisterResponseDTO registerUser(RegisterRequestDTO requestDTO) {
        validateRegisterRequest(requestDTO);

        User newUser = new User(
                null,
                requestDTO.getUsername(),
                requestDTO.getNome(),
                requestDTO.getEmail(),
                null,
                null,
                LocalDate.now(),
                Boolean.TRUE,
                encoder.encode(requestDTO.getSenha())
        );

        User savedUser = userRepository.save(newUser);

        return RegisterResponseDTO.success(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getProfilePicture()
        );
    }

    private void validateRegisterRequest(RegisterRequestDTO requestDTO) {
        String password = requestDTO.getSenha();
        String confirmPassword = requestDTO.getConfirmacaoSenha();
        String email = requestDTO.getEmail();
        String username = requestDTO.getUsername();

        if (userRepository.existsByUsername(username)) {
            throw new UserAlreadyExistsException("Username já está em uso!");
        }
        
        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException("Email já está em uso!");
        }
        
        if (password.length() < TAMANHO_MINIMO_SENHA) {
            throw new InvalidPasswordException("Sua senha precisa ter pelo menos 8 caracteres");
        }
        
        if (!password.equals(confirmPassword)) {
            throw new InvalidPasswordException("Senhas não conferem");
        }
    }

}

