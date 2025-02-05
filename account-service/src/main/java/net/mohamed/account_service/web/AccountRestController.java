package net.mohamed.account_service.web;

import net.mohamed.account_service.clients.CustomerRestClient;
import net.mohamed.account_service.entities.BankAccount;
import net.mohamed.account_service.model.Customer;
import net.mohamed.account_service.repositories.BankAccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;
    private CustomerRestClient customerRestClient;

    public AccountRestController(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient) {
        this.bankAccountRepository = bankAccountRepository;
        this.customerRestClient = customerRestClient;
    }

    @GetMapping("/accounts")
    public List<BankAccount> accountList(){
        List<BankAccount> accountList = bankAccountRepository.findAll();
        accountList.forEach(bankAccount -> {
            bankAccount.setCustomer(customerRestClient.findCustomerById(bankAccount.getCustomerId()));
        });
        return accountList;
    }


    @GetMapping("/accounts/{id}")
    public BankAccount bankAccountById(@PathVariable String id){
        BankAccount bankAccount=bankAccountRepository.findById(id).get();
        Customer customer=customerRestClient.findCustomerById(bankAccount.getCustomerId());
        bankAccount.setCustomer(customer);
        return bankAccount;
    }



}
