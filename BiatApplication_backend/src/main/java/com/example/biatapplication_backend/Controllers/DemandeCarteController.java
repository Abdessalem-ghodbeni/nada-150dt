package com.example.biatapplication_backend.Controllers;

import com.example.biatapplication_backend.Entities.DemandeCarteBancaire;
import com.example.biatapplication_backend.Exception.RessourceNotFound;
import com.example.biatapplication_backend.Services.IDemandeCarteBancaireImpl;
import com.example.biatapplication_backend.Services.IDemandeCarteBancaireSerives;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/demandeCarte")
@RequiredArgsConstructor

public class DemandeCarteController {

    private final IDemandeCarteBancaireSerives demandeCarteBancaireService;
    @PostMapping(path = "/add")
    public ResponseEntity<DemandeCarteBancaire> AjouterNouvelleDemandeCarte(@RequestBody DemandeCarteBancaire demandeCarteBancaire) {
        try {
            DemandeCarteBancaire nouvelleChambre = demandeCarteBancaireService.ajouterDemande(demandeCarteBancaire);
            return new ResponseEntity<>(nouvelleChambre, HttpStatus.CREATED);
        } catch (RessourceNotFound exception) {
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
