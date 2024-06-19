package com.example.biatapplication_backend.Repository;

import com.example.biatapplication_backend.Entities.Currency;
import com.example.biatapplication_backend.Entities.Officer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency,Long> {
}
