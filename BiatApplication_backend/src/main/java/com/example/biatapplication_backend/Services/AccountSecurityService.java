package com.example.biatapplication_backend.Services;


import com.example.biatapplication_backend.Dto.AccountDetails;
import com.example.biatapplication_backend.Dto.AccountLoginDto;
import com.example.biatapplication_backend.Entities.User;
import com.example.biatapplication_backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class AccountSecurityService implements UserDetailsService {
    @Autowired
    private UserRepository userInfoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Override
    public AccountDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userInfoEmail = userInfoRepository.findById(Long.valueOf(username));
        return userInfoEmail.map(AccountDetails::new).orElseThrow(()-> new UsernameNotFoundException("User not found"+username));

    }

    public AccountLoginDto loginSucces(String username){
        AccountLoginDto accountLoginDto = new AccountLoginDto();
        Long id= Long.valueOf(username);
        User UserLogin= userInfoRepository.findById(id).orElse(null);
        if(UserLogin!= null){
            accountLoginDto.setMatricule(UserLogin.getMatricule());
            accountLoginDto.setRole(UserLogin.getRole());
            accountLoginDto.setPrenom(UserLogin.getPrenom());
            accountLoginDto.setNom(UserLogin.getNom());
            accountLoginDto.setPassword(UserLogin.getPassword());
            accountLoginDto.setMail(UserLogin.getMail());
            accountLoginDto.setToken(jwtService.generateToken(username));

        }
        return accountLoginDto;
    }

    public String addUser(User userInfo){
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return "User added successfully";
    }
    public List<User> getAllUser(){
        return userInfoRepository.findAll();
    }
    public User getUser(Long id){
        return userInfoRepository.findById(id).get();
    }


}