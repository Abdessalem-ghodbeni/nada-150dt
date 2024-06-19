package com.example.biatapplication_backend.Services;


import com.example.biatapplication_backend.Dto.AccountDto;
import com.example.biatapplication_backend.Dto.CustomerDto;
import com.example.biatapplication_backend.Entities.*;
import com.example.biatapplication_backend.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService{


    @Autowired
    CountryRepository countryRepository;
    @Autowired
    OfficerRepository officerRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AccountRepository accountRepository;


    @Override
    public List<Country> getAllNationality() {
        return countryRepository.findAll();
    }

    @Override
    public Officer getOfficer(Long id) {
        for (Officer officer : officerRepository.findAll()){

            for (User user :officer.getEmployes()){
                System.out.println(user.getMatricule());
                if(user.getMatricule().intValue() == id.intValue()){
                    System.out.println("hello");
                    return officer;
                }
            }
        }
        return null;
    }

    @Override
    public Customer addCustomer(Long idNationality, Long idOfficer, Customer customer) {
        Officer officer = officerRepository.findById(idOfficer).orElse(null);
        Country country = countryRepository.findById(idNationality).orElse(null);

        if(officer!=null && country != null){
            country.getCustomers().add(customer);
            officer.getCustomers().add(customer);

            return customerRepository.save(customer);
        }

        return null;
    }

    @Override
    public List<CustomerDto> getAll() {

        List<CustomerDto>  customerDtoList = new ArrayList<>();

        for (Customer customer : customerRepository.findAll())
        {
            CustomerDto customerDto = new CustomerDto();
            customerDto.setId(customer.getId());
            customerDto.setGender(customer.getGender());
            customerDto.setAddress(customer.getAddress());
            customerDto.setName(customer.getName());
            customerDto.setMail(customer.getMail());
            customerDto.setDateBirthCreation(customer.getDateBirthCreation());
            customerDto.setTel(customer.getTel());
            customerDto.setPostCode(customer.getPostCode());
            customerDto.setType(customer.getType());
            customerDto.setLegalDocName(customer.getLegalDocName());
            customerDto.setLegalId(customer.getLegalId());



            for (Country country : countryRepository.findAll()){
                for(Customer customer1 : country.getCustomers()){
                    if(customer1.getId().intValue() == customer.getId().intValue()){
                        customerDto.setCountry(country);
                    }
                }
            }
            for (Officer officer : officerRepository.findAll()){
                for(Customer customer1 : officer.getCustomers()){
                    if(customer1.getId().intValue() == customer.getId().intValue()){
                        customerDto.setOfficer(officer);
                    }
                }
            }

            customerDtoList.add(customerDto);


        }


        return customerDtoList;
    }

    @Override
    public CustomerDto getDetailsCustomer(Long idCustomer) {
        Customer customer = customerRepository.findById(idCustomer).orElse(null);


        if(customer!=null)
        {
            CustomerDto customerDto = new CustomerDto();
            customerDto.setId(customer.getId());
            customerDto.setGender(customer.getGender());
            customerDto.setAddress(customer.getAddress());
            customerDto.setName(customer.getName());
            customerDto.setMail(customer.getMail());
            customerDto.setDateBirthCreation(customer.getDateBirthCreation());
            customerDto.setTel(customer.getTel());
            customerDto.setPostCode(customer.getPostCode());
            customerDto.setType(customer.getType());
            customerDto.setLegalDocName(customer.getLegalDocName());
            customerDto.setLegalId(customer.getLegalId());



            for (Country country : countryRepository.findAll()){
                for(Customer customer1 : country.getCustomers()){
                    if(customer1.getId().intValue() == customer.getId().intValue()){
                        customerDto.setCountry(country);
                    }
                }
            }
            for (Officer officer : officerRepository.findAll()){
                for(Customer customer1 : officer.getCustomers()){
                    if(customer1.getId().intValue() == customer.getId().intValue()){
                        customerDto.setOfficer(officer);
                    }
                }
            }

            return  customerDto;

        }
        return  null;
    }

    @Override
    public Customer updateCustomer(Long idNationality, Long idOfficer, Customer customer) {


        for (Country country : countryRepository.findAll()){

            if(country!=null) {
                for (Customer customer1 : country.getCustomers()) {

                    if (customer1 != null)
                        if (customer1.getId().intValue() == customer.getId().intValue()) {
                            if (idNationality.intValue() != country.getId().intValue()) {
                                Country countryNew = countryRepository.findById(idNationality).orElse(null);
                                if (countryNew != null) {
                                    countryNew.getCustomers().add(customer1);
                                    countryRepository.save(countryNew);

                                }

                            }

                        }
                }
            }

        }

        return customerRepository.save(customer);



    }

    @Override
    public List<Currency> getAllCurrency() {
       return currencyRepository.findAll();
    }

    @Override
    public List<Customer> getCustomerNoAffect() {

        List<Customer> customerListNoAffect = new ArrayList<>();

        if(accountRepository.findAll().isEmpty())
        {
            return customerRepository.findAll();
        }
        else {
            for (Customer customer : customerRepository.findAll()) {
                    if(customer.getAccount()==null){
                        customerListNoAffect.add(customer);
                    }
                }
            }

        return customerListNoAffect;
    }

    @Override
    public void addAccount(Long idCustomer, Long idCurrency, Long idOfficer, Account account) {


        Customer customer = customerRepository.findById(idCustomer).orElse(null);

        if(customer!=null){


            Officer officer = officerRepository.findById(idOfficer).orElse(null);
            Currency currency = currencyRepository.findById(idCurrency).orElse(null);


            account.setCustomer(customer);
            accountRepository.save(account);
            if(officer != null && currency!=null){
                officer.getAccounts().add(account);
                currency.getAccounts().add(account);

                officerRepository.save(officer);
                currencyRepository.save(currency);

            }
        }

    }

    @Override
    public List<AccountDto> getAccounts() {

        List<AccountDto>  accountDtoList = new ArrayList<>();

        for (Account account : accountRepository.findAll())
        {
            AccountDto accountDto = new AccountDto();
            accountDto.setId(account.getId());
            accountDto.setCategory(account.getCategory());
            accountDto.setTitle(account.getTitle());
            accountDto.setClosureDate(account.getClosureDate());
            accountDto.setOpeningDate(account.getOpeningDate());
            accountDto.setWorkingBalance(account.getWorkingBalance());
            accountDto.setCustomer(account.getCustomer());
            for (Currency currency : currencyRepository.findAll()){
                for(Account account1 : currency.getAccounts()){
                    if(account1.getId().intValue() == account.getId().intValue()){
                        accountDto.setCurrency(currency);
                    }
                }
            }
            for (Officer officer : officerRepository.findAll()){
                for(Account account1 : officer.getAccounts()){
                    if(account1.getId().intValue() == account.getId().intValue()){
                        accountDto.setOfficer(officer);
                    }
                }
            }

            accountDtoList.add(accountDto);


        }


        return accountDtoList;
    }

    @Override
    public AccountDto getDetailsAccount(Long idAccount) {

        Account account = accountRepository.findById(idAccount).orElse(null);


        if(account!=null)
        {
            AccountDto accountDto = new AccountDto();
            accountDto.setId(account.getId());
            accountDto.setTitle(account.getTitle());
            accountDto.setWorkingBalance(account.getWorkingBalance());
            accountDto.setOpeningDate(account.getOpeningDate());
            accountDto.setCategory(account.getCategory());
            accountDto.setClosureDate(account.getClosureDate());
            accountDto.setCustomer(account.getCustomer());



            for (Currency currency : currencyRepository.findAll()){
                for(Account account1 : currency.getAccounts()){
                    if(account1.getId().intValue() == account.getId().intValue()){
                        accountDto.setCurrency(currency);
                    }
                }
            }
            for (Officer officer : officerRepository.findAll()){
                for(Account account1 : officer.getAccounts()){
                    if(account1.getId().intValue() == account.getId().intValue()){
                        accountDto.setOfficer(officer);
                    }
                }
            }

            return  accountDto;

        }
        return  null;
    }

    @Override
    public Account updateAccount(Long idAccount, String newtitle) {
        Account account = accountRepository.findById(idAccount).orElse(null);

        if(account!=null)
        {
            account.setTitle(newtitle);
            return accountRepository.save(account);
        }

        return null;
    }
}
