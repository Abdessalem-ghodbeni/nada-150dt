package com.example.biatapplication_backend.Services;

import com.example.biatapplication_backend.Entities.DemandeCarteBancaire;

import java.util.List;

public interface IDemandeCarteBancaireSerives {
    public DemandeCarteBancaire ajouterDemande(DemandeCarteBancaire demandeCarteBancaire);
    public void deleteDemandeCarteBancaire(Long id);
    public DemandeCarteBancaire updateDemandeCarteBancaire(DemandeCarteBancaire demandeCarteBancaire);
    public List<DemandeCarteBancaire> getDemandesRejetees();
    public List<DemandeCarteBancaire> getDemandeEnCours();
    public List<DemandeCarteBancaire> getDemandesACCEPTED();
public void updateDemandeCarteBancaireStatus(Long id) ;
    public void accpetedCarteBancaire(Long id) ;
}
