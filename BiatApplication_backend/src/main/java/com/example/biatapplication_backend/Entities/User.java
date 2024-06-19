package com.example.biatapplication_backend.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter @Entity @AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Id
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Matricule ;
    String nom;
    String prenom;
    String mail;
    String password;
    @Enumerated(EnumType.STRING)
    Role role;




}
