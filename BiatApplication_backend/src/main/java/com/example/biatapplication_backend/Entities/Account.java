package com.example.biatapplication_backend.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Account implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    @Enumerated(EnumType.STRING)
    Category category;
    int workingBalance = 0;
    Date openingDate;
    Date closureDate;


    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "customerId")
    Customer customer;
}
