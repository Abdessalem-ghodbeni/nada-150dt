package com.example.biatapplication_backend.Controllers;


import com.example.biatapplication_backend.Dto.AccountLoginDto;
import com.example.biatapplication_backend.Dto.AuthRequest;
import com.example.biatapplication_backend.Entities.User;
import com.example.biatapplication_backend.Services.AccountSecurityService;
import com.example.biatapplication_backend.Services.IAccountService;
import com.example.biatapplication_backend.Services.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {


    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private IAccountService accountIService;

    @Autowired
    AccountSecurityService accountSecurityService;


    @PostMapping("/login")
    public AccountLoginDto login(@RequestBody AuthRequest authRequest){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if(authenticate.isAuthenticated()){
            return accountSecurityService.loginSucces(authRequest.getUsername());
        }else {
            throw new UsernameNotFoundException("Invalid user request");
        }
    }

/*
    @GetMapping("/admin")
    public List<AccountLoginDto> getAllAccount(){
        return accountIService.getAllUser();
    }


    @PostMapping("/signup")
    public User signup(@RequestBody User admin) {
        return accountIService.signup(admin);
    }

    @PostMapping("/addEmploye")
    public User addEmplye(@RequestBody User employe) {
        return accountIService.addEmploye(employe);
    }


    @DeleteMapping("/deleteEmploye/{idAccount}")
    public void deleteEmploye(@PathVariable Long idAccount){
        accountIService.deleteEmploye(idAccount);
    }
*/
}
