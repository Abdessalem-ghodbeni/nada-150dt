package com.example.biatapplication_backend.Repository;

import com.example.biatapplication_backend.Entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Long> {
}
