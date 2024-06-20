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
//    @Override
//    @Transactional
//    public DemandeCarteBancaire ajouterDemande(DemandeCarteBancaire demandeCarteBancaire) {
//
//        demandeCarteBancaire.setStatus(Status.EN_COURS);
//        demandeCarteBancaire.setDateDemande(new Date());
//
//        Officer officer = demandeCarteBancaire.getAgent();
//        Customer client = demandeCarteBancaire.getClient();
//        Account acount=demandeCarteBancaire.getCompte();
//
//        demandeCarteBancaire = demandeCarteBancaireRepository.save(demandeCarteBancaire);
//
//
//        officer.getDemandeCarteBancaires().add(demandeCarteBancaire);
//        client.getDemandeCarteBancaires().add(demandeCarteBancaire);
//        acount.getDemandeCarteBancaires().add(demandeCarteBancaire);
//
//        officerRepository.save(officer);
//        customerRepository.save(client);
//    accountRepository.save(acount);
//        return demandeCarteBancaireRepository.save(demandeCarteBancaire);
//
//    }
@Override
@Transactional
public DemandeCarteBancaire ajouterDemande(DemandeCarteBancaire demandeCarteBancaire) {

    demandeCarteBancaire.setStatus(Status.EN_COURS);
    demandeCarteBancaire.setDateDemande(new Date());

    // Ensure Officer, Customer, and Account objects are properly fetched or created
    Long officerId = demandeCarteBancaire.getAgent().getId();
    Long customerId = demandeCarteBancaire.getClient().getId();
    Long accountId = demandeCarteBancaire.getCompte().getId();

    Officer officer = officerRepository.findById(officerId).orElseThrow(() ->
            new IllegalArgumentException("Officer with ID " + officerId + " not found"));
    Customer customer = customerRepository.findById(customerId).orElseThrow(() ->
            new IllegalArgumentException("Customer with ID " + customerId + " not found"));
    Account account = accountRepository.findById(accountId).orElseThrow(() ->
            new IllegalArgumentException("Account with ID " + accountId + " not found"));

    demandeCarteBancaire.setAgent(officer);
    demandeCarteBancaire.setClient(customer);
    demandeCarteBancaire.setCompte(account);

    demandeCarteBancaire = demandeCarteBancaireRepository.save(demandeCarteBancaire);

    officer.getDemandeCarteBancaires().add(demandeCarteBancaire);
    customer.getDemandeCarteBancaires().add(demandeCarteBancaire);
    account.getDemandeCarteBancaires().add(demandeCarteBancaire);

    // Save Officer, Customer, and Account only if necessary
    if (!officer.getDemandeCarteBancaires().contains(demandeCarteBancaire)) {
        officerRepository.save(officer);
    }
    if (!customer.getDemandeCarteBancaires().contains(demandeCarteBancaire)) {
        customerRepository.save(customer);
    }
    if (!account.getDemandeCarteBancaires().contains(demandeCarteBancaire)) {
        accountRepository.save(account);
    }

    return demandeCarteBancaireRepository.save(demandeCarteBancaire);
}
}
