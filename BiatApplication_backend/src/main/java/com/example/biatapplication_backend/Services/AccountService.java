package com.example.biatapplication_backend.Services;

import com.example.biatapplication_backend.Dto.AccountLoginDto;
import com.example.biatapplication_backend.Entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements IAccountService {
    @Override
    public List<AccountLoginDto> getAllUser() {
        return null;
    }

    @Override
    public User signup(User user) {
        return null;
    }

    @Override
    public void deleteEmploye(Long idAccount) {

    }
}
