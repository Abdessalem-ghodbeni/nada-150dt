package com.example.biatapplication_backend.Repository;

import com.example.biatapplication_backend.Entities.DemandeCarteBancaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDemandeCarteBancaire extends JpaRepository<DemandeCarteBancaire,Long> {
}
