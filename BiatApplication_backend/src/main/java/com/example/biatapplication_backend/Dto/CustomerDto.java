package com.example.biatapplication_backend.Dto;

import com.example.biatapplication_backend.Entities.Country;
import com.example.biatapplication_backend.Entities.CustomerType;
import com.example.biatapplication_backend.Entities.Officer;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter @Data
public class CustomerDto {

    Long id;
    @Enumerated(EnumType.STRING)
    CustomerType type;
    String name;
    String gender;
    Date dateBirthCreation;
    String address;
    Long postCode;
    String legalDocName;
    String legalId;
    String tel ;
    String mail;

    Officer officer;
    Country country;


}
