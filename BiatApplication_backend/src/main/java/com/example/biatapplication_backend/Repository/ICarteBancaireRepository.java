package com.example.biatapplication_backend.Repository;

import com.example.biatapplication_backend.Entities.CarteBancaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICarteBancaireRepository extends JpaRepository<CarteBancaire,Long> {
}
