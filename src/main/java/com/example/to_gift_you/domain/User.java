package com.example.to_gift_you.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @Size(min = 3, max = 30, message = "Username deve ter entre 3 e 30 caracteres")
    private String username;

    @Column(nullable = false)
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    private String name;

    @Column(unique = true, nullable = false)
    @Email(message = "Email inválido")
    private String email;

    @Column(name = "profilepicture")
    private String profilePicture;

    @Size(max = 500, message = "Biografia não pode ter mais de 500 caracteres")
    @Column(name = "biography")
    private String biography;

    @Column(name = "createdate")
    private LocalDate createDate;

    @Column(name = "active")
    private Boolean active;

    @Column(nullable = false)
    @Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
    private String password;
}
