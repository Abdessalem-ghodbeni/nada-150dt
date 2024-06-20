package com.example.biatapplication_backend.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DemandeCompteBancaire")
public class DemandeCarteBancaire implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Customer client;

    @ManyToOne
    @JoinColumn(name = "agent_id", nullable = false)
    private Officer agent;

    @ManyToOne
    @JoinColumn(name = "compte_id", nullable = false)
    private Account compte;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDemande;

    private String rib;
    private String category;

    private String nomComplet;
    private String adresse;
    private String numeroTelephone;
    private String Cin;
    private String emailAdress;

private boolean premierDemande;
}
