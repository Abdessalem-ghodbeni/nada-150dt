package com.example.biatapplication_backend.Repository;

import com.example.biatapplication_backend.Entities.Customer;
import com.example.biatapplication_backend.Entities.Officer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
