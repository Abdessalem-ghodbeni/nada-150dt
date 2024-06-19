package com.example.biatapplication_backend.Services;

import com.example.biatapplication_backend.Dto.AccountLoginDto;
import com.example.biatapplication_backend.Entities.User;

import java.util.List;

public interface IAccountService {
    public List<AccountLoginDto> getAllUser();
    public User signup(User user);
    //public Account addEmploye(User account);


    void deleteEmploye(Long idAccount);
}
