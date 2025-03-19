package com.Bannking.BankingApplication.Services;

import com.Bannking.BankingApplication.Model.Account;
import com.Bannking.BankingApplication.Repositoty.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepo repo;

    public Account createAccount(Account account){
        return repo.save(account);
    }

    public Optional<Account> getAccount(Long id){
        return repo.findById(id);
    }

    public Account deposit(Long id, double amount) {
        Account account = getAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(account.getBalance() + amount);
        return repo.save(account);
    }

    public Account withdraw(Long id, double amount) {
        Account account = getAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));
        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds");
        }
        account.setBalance(account.getBalance() - amount);
        return repo.save(account);
    }

    public Account delectAccount( Long id){
        Account account = repo.findById(id).orElseThrow(()->new RuntimeException(" Account not found"));

        repo.delete(account);

        return repo.findAll().get(0);
    }

    public List<Account> getAllAccounts(){
        return repo.findAll();
    }
}
