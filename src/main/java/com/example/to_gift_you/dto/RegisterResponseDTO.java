package com.example.to_gift_you.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponseDTO {
    
    private Long id;
    private String nome;
    private String email;
    private String fotoPerfil;
    private String mensagem;
    
    public static RegisterResponseDTO success(Long id, String nome, String email, String fotoPerfil) {
        return RegisterResponseDTO.builder()
                .id(id)
                .nome(nome)
                .email(email)
                .fotoPerfil(fotoPerfil)
                .mensagem("Cadastro realizado com sucesso!")
                .build();
    }
}
