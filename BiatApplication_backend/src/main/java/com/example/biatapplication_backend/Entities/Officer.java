package com.example.biatapplication_backend.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Getter @Setter
@Entity @AllArgsConstructor
@NoArgsConstructor
public class Officer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;

    @OneToMany(fetch = FetchType.EAGER )
    @JoinColumn(name = "agence")
    List<User> employes= new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "officer")
    List<Customer> customers = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "officer")
    List<Account> accounts =new ArrayList<>();

}
