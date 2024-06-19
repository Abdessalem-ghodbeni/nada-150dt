package com.example.biatapplication_backend.Dto;

import com.example.biatapplication_backend.Entities.Category;
import com.example.biatapplication_backend.Entities.Currency;
import com.example.biatapplication_backend.Entities.Customer;
import com.example.biatapplication_backend.Entities.Officer;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter @Data
public class AccountDto {

    Long id;
    String title;
    @Enumerated(EnumType.STRING)
    Category category;
    int workingBalance = 0;
    Date openingDate;
    Date closureDate;

    Officer officer;
    Currency currency;
    Customer customer;

}
