package com.example.biatapplication_backend.Repository;

import com.example.biatapplication_backend.Entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}
