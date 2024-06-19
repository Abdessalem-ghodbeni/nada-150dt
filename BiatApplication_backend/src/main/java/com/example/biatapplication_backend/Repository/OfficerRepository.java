package com.example.biatapplication_backend.Repository;

import com.example.biatapplication_backend.Entities.Officer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficerRepository extends JpaRepository<Officer,Long> {
}
