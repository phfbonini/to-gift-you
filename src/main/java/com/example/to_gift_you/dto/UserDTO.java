package com.example.to_gift_you.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class UserDTO {
        private Long id;
        private String name;
        private String username;
        private String email;
        private String profilePicture;
        private String biography;
        private String message;
        private LocalDate createData;
        private String password;
}



