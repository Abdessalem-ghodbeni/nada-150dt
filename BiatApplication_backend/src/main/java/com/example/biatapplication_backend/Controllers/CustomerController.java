package com.example.biatapplication_backend.Controllers;

import com.example.biatapplication_backend.Dto.AccountDto;
import com.example.biatapplication_backend.Dto.CustomerDto;
import com.example.biatapplication_backend.Entities.*;
import com.example.biatapplication_backend.Services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/customer")
public class CustomerController {

    @Autowired
    IUserService iUserService;


    @GetMapping("/getAllNationality")
    List<Country> getAllNationality(){
        return iUserService.getAllNationality();
    }

    @GetMapping("/getAllCurrency")
    List<Currency> getAllCurrency(){
        return iUserService.getAllCurrency();
    }


    @GetMapping("/getOfficer/{id}")
    public Officer getOfficer(@PathVariable Long id){

        return iUserService.getOfficer(id);
    }

    @PostMapping("/addCustomer/{idNationality}/{idOfficer}")
    public Customer addCustomer(@PathVariable Long idNationality , @PathVariable Long idOfficer , @RequestBody Customer customer ){
        return iUserService.addCustomer(idNationality,idOfficer,customer);

    }

    @PostMapping("/updateCustomer/{idNationality}/{idOfficer}")
    public Customer updateCustomer(@PathVariable Long idNationality , @PathVariable Long idOfficer , @RequestBody Customer customer ){
        return iUserService.updateCustomer(idNationality,idOfficer,customer);

    }


    @GetMapping("/getAll")
    public List<CustomerDto> getAll(){
        return iUserService.getAll();
    }


    @GetMapping("/getDetailsCustomer/{idCustomer}")
    public CustomerDto getDetailsCustomer(@PathVariable Long idCustomer){
        return iUserService.getDetailsCustomer(idCustomer);
    }

    @GetMapping("/getDetailsAccount/{idAccount}")
    public AccountDto getDetailsAccount(@PathVariable Long idAccount){
        return iUserService.getDetailsAccount(idAccount);
    }

    @GetMapping("/getCustomerNoAffect")
    public List<Customer> getCustomerNoAffect(){
        return iUserService.getCustomerNoAffect();
    }

    @PostMapping("/addAccount/{idCustomer}/{idCurrency}/{idOfficer}")
    public void addAccount(@PathVariable Long idCustomer , @PathVariable Long idCurrency , @PathVariable Long idOfficer , @RequestBody Account account){

        iUserService.addAccount(idCustomer,idCurrency,idOfficer,account);
    }


    @GetMapping("/getAccounts")
    public List<AccountDto> getAccounts(){
       return iUserService.getAccounts();
    }

    @PostMapping("/updateAccount/{idAccount}/{newtitle}")
    public Account updateAccount(@PathVariable Long idAccount , @PathVariable String newtitle)    {
        return iUserService.updateAccount(idAccount,newtitle);
    }
}
