package com.example.biatapplication_backend.Services;

import com.example.biatapplication_backend.Dto.AccountDto;
import com.example.biatapplication_backend.Dto.CustomerDto;
import com.example.biatapplication_backend.Entities.*;

import java.util.List;

public interface IUserService {

    List<Country> getAllNationality();

    Officer getOfficer(Long id);

    Customer addCustomer(Long idNationality, Long idOfficer, Customer customer);

    List<CustomerDto> getAll();

    CustomerDto getDetailsCustomer(Long idCustomer);

    Customer updateCustomer(Long idNationality, Long idOfficer, Customer customer);

    List<Currency> getAllCurrency();

    List<Customer> getCustomerNoAffect();

    void addAccount(Long idCustomer, Long idCurrency, Long idOfficer, Account account);

    List<AccountDto> getAccounts();

    AccountDto getDetailsAccount(Long idAccount);

    Account updateAccount(Long idAccount, String newtitle);
}
