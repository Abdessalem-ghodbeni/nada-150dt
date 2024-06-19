package com.example.biatapplication_backend.Dto;

import com.example.biatapplication_backend.Entities.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AccountLoginDto {


    Long Matricule ;
    String nom;
    String prenom;
    String mail;
    String password;
    @Enumerated(EnumType.STRING)
    Role role;
    String token;
}
