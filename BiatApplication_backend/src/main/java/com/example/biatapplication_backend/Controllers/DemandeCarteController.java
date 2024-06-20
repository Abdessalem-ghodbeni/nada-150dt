package com.example.biatapplication_backend.Controllers;

import com.example.biatapplication_backend.Entities.DemandeCarteBancaire;
import com.example.biatapplication_backend.Exception.RessourceNotFound;
import com.example.biatapplication_backend.Services.IDemandeCarteBancaireImpl;
import com.example.biatapplication_backend.Services.IDemandeCarteBancaireSerives;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> SupprimerBloc(@PathVariable("id") long idbloc) {
        try {
        demandeCarteBancaireService.deleteDemandeCarteBancaire(idbloc);
            return ResponseEntity.ok("Demande deleted avec succé");
        } catch (RessourceNotFound exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }

    }
    @PutMapping(path = "/edit")
    public ResponseEntity<?> ModifierBloc(@RequestBody DemandeCarteBancaire bloc) {
        try {
            DemandeCarteBancaire blocUpdating = demandeCarteBancaireService.updateDemandeCarteBancaire(bloc);
            return new ResponseEntity<>(blocUpdating, HttpStatus.OK);
        } catch (RessourceNotFound exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/allDemande/rejected")
    public ResponseEntity<?> getAllChambre() {

        try {
            List<DemandeCarteBancaire> chambres = demandeCarteBancaireService.getDemandesRejetees();
            if (chambres.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body("Liste est vide ");
            }
            return ResponseEntity.ok(chambres);
        } catch (RessourceNotFound exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("quelque chose mal passé");
        }
    }

    @GetMapping(path = "/allDemande/accepted")
    public ResponseEntity<?> getAllChambreAccepted() {

        try {
            List<DemandeCarteBancaire> chambres = demandeCarteBancaireService.getDemandesACCEPTED();
            if (chambres.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body("Liste est vide ");
            }
            return ResponseEntity.ok(chambres);
        } catch (RessourceNotFound exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("quelque chose mal passé");
        }
    }

    @PutMapping(path = "/refuser/demande/{idbloc}")
    public ResponseEntity<?> RefuserDemande( @PathVariable("idbloc") long idbloc) {
        try {
            demandeCarteBancaireService.updateDemandeCarteBancaireStatus(idbloc);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RessourceNotFound exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PutMapping(path = "/accepter/demande/{idbloc}")
    public ResponseEntity<?> AccepterDemande( @PathVariable("idbloc") long idbloc) {
        try {
            demandeCarteBancaireService.accpetedCarteBancaire(idbloc);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RessourceNotFound exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
