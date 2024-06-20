package com.example.biatapplication_backend.Services;

import com.example.biatapplication_backend.Entities.*;
import com.example.biatapplication_backend.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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
    @Autowired
    private ICarteBancaireRepository carteBancaireRepository;

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

    @Override
    @Transactional
    public void deleteDemandeCarteBancaire(Long id) {

        DemandeCarteBancaire demandeCarteBancaire = demandeCarteBancaireRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("DemandeCarteBancaire with ID " + id + " not found"));

        // Remove from related entities
        Officer officer = demandeCarteBancaire.getAgent();
        Customer customer = demandeCarteBancaire.getClient();
        Account account = demandeCarteBancaire.getCompte();

        officer.getDemandeCarteBancaires().remove(demandeCarteBancaire);
        customer.getDemandeCarteBancaires().remove(demandeCarteBancaire);
        account.getDemandeCarteBancaires().remove(demandeCarteBancaire);

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

        // Finally, delete the DemandeCarteBancaire
        demandeCarteBancaireRepository.delete(demandeCarteBancaire);
    }

    @Override
    public DemandeCarteBancaire updateDemandeCarteBancaire(DemandeCarteBancaire demandeCarteBancaire) {

        Long id = demandeCarteBancaire.getId();

        // Retrieve existing DemandeCarteBancaire object
        DemandeCarteBancaire existingDemandeCarteBancaire = demandeCarteBancaireRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("DemandeCarteBancaire with ID " + id + " not found"));

        // Update fields with new values
        existingDemandeCarteBancaire.setStatus(demandeCarteBancaire.getStatus());
        existingDemandeCarteBancaire.setRib(demandeCarteBancaire.getRib());
        existingDemandeCarteBancaire.setCategory(demandeCarteBancaire.getCategory());
        existingDemandeCarteBancaire.setNomComplet(demandeCarteBancaire.getNomComplet());
        existingDemandeCarteBancaire.setAdresse(demandeCarteBancaire.getAdresse());
        existingDemandeCarteBancaire.setNumeroTelephone(demandeCarteBancaire.getNumeroTelephone());
        existingDemandeCarteBancaire.setCin(demandeCarteBancaire.getCin());
        existingDemandeCarteBancaire.setEmailAdress(demandeCarteBancaire.getEmailAdress());
        existingDemandeCarteBancaire.setPremierDemande(demandeCarteBancaire.isPremierDemande());

        // Save the updated DemandeCarteBancaire object
        return demandeCarteBancaireRepository.save(existingDemandeCarteBancaire);
    }

    @Override
    public List<DemandeCarteBancaire> getDemandesRejetees() {
        return demandeCarteBancaireRepository.findByStatus(Status.REJECTED);
    }

    @Override
    public List<DemandeCarteBancaire> getDemandeEnCours() {
        return demandeCarteBancaireRepository.findByStatus(Status.EN_COURS);
    }

    @Override
    public List<DemandeCarteBancaire> getDemandesACCEPTED() {
        return demandeCarteBancaireRepository.findByStatus(Status.ACCEPTED);
    }

    @Override
    @Transactional
    public void updateDemandeCarteBancaireStatus(Long id) {
        DemandeCarteBancaire demandeCarteBancaire = demandeCarteBancaireRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("DemandeCarteBancaire with ID " + id + " not found"));


            demandeCarteBancaire.setStatus(Status.REJECTED);
            demandeCarteBancaireRepository.save(demandeCarteBancaire);

    }

    @Override
    @Transactional
    public void accpetedCarteBancaire(Long id) {
        DemandeCarteBancaire demandeCarteBancaire = demandeCarteBancaireRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("DemandeCarteBancaire with ID " + id + " not found"));


        demandeCarteBancaire.setStatus(Status.ACCEPTED);


        demandeCarteBancaireRepository.save(demandeCarteBancaire);
        CarteBancaire carteBancaire = new CarteBancaire();
        carteBancaire.setRib(demandeCarteBancaire.getRib());
        carteBancaire.setNomComplet(demandeCarteBancaire.getNomComplet());
Customer customer=demandeCarteBancaire.getClient();
carteBancaire.setCustomer(customer);
customer.setCarteBancaire(carteBancaire);
        carteBancaireRepository.save(carteBancaire);
        customerRepository.save(customer);
    }
}
