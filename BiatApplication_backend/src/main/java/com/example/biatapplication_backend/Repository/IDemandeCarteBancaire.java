package com.example.biatapplication_backend.Repository;

import com.example.biatapplication_backend.Entities.DemandeCarteBancaire;
import com.example.biatapplication_backend.Entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDemandeCarteBancaire extends JpaRepository<DemandeCarteBancaire,Long> {
    List<DemandeCarteBancaire> findByStatus(Status status);
}
