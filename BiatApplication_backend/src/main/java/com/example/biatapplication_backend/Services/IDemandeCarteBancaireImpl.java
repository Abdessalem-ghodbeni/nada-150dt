package com.example.biatapplication_backend.Services;

import com.example.biatapplication_backend.Entities.*;
import com.example.biatapplication_backend.Repository.AccountRepository;
import com.example.biatapplication_backend.Repository.CustomerRepository;
import com.example.biatapplication_backend.Repository.IDemandeCarteBancaire;
import com.example.biatapplication_backend.Repository.OfficerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class IDemandeCarteBancaireImpl implements IDemandeCarteBancaireSerives{
//    private final IDemandeCarteBancaire demandeCarteBancaireRepository;
//    private final OfficerRepository officerRepository;
//    private final AccountRepository accountRepository;
//    private final CustomerRepository customerRepository;
@Autowired
private IDemandeCarteBancaire demandeCarteBancaireRepository;
    @Autowired
    private OfficerRepository officerRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    @Transactional
    public DemandeCarteBancaire ajouterDemande(DemandeCarteBancaire demandeCarteBancaire) {

        demandeCarteBancaire.setStatus(Status.EN_COURS);
        demandeCarteBancaire.setDateDemande(new Date());
        Account compte = demandeCarteBancaire.getCompte();
        Officer officer = demandeCarteBancaire.getAgent();
        Customer client = demandeCarteBancaire.getClient();
        Account acount=demandeCarteBancaire.getCompte();

        demandeCarteBancaire = demandeCarteBancaireRepository.save(demandeCarteBancaire);


        officer.getDemandeCarteBancaires().add(demandeCarteBancaire);
        client.getDemandeCarteBancaires().add(demandeCarteBancaire);
        acount.getDemandeCarteBancaires().add(demandeCarteBancaire);

        officerRepository.save(officer);
        customerRepository.save(client);
    accountRepository.save(acount);
        return demandeCarteBancaireRepository.save(demandeCarteBancaire);

    }
}
