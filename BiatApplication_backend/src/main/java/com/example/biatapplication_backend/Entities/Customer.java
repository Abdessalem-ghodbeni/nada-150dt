package com.example.biatapplication_backend.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Customer implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Enumerated(EnumType.STRING)
    CustomerType type;
    String name;
    String gender;
    Date dateBirthCreation;
    String address;
    Long postCode;
    String legalDocName;
    String legalId;
    String tel ;
    String mail;
    @OneToOne(mappedBy = "customer")
    private CarteBancaire carteBancaire;
    @OneToOne(mappedBy = "customer")
    Account account;
@OneToMany(mappedBy = "client")
    private List<DemandeCarteBancaire> demandeCarteBancaires;

}
