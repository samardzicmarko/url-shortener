package com.rest.url_shortener.controllers;

import com.rest.url_shortener.model.Account;
import com.rest.url_shortener.repository.AccountRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AccountController {

    private final AccountRepository repository;

    AccountController(AccountRepository repository){
        this.repository = repository;
    }

    @GetMapping("/account")
    List<Account> all(){
        return repository.findAll();
    }

    @PostMapping(value = "/account", consumes = "application/json", produces = "application/json")
    public Map<String, String> addAccount(@RequestBody  Account account){

        if (account.getAccountId() == null){
            HashMap<String, String> map = new HashMap<>();
            map.put("Error:", "AccountId is required");
            return map;
        }
        if (!repository.existsAccountByAccountId(account.getAccountId())) {
                System.out.print(account);
                Account newAcc = new Account();
                newAcc.setAccountId(account.getAccountId());
                newAcc.setPassword(account.generatePassword());
                HashMap<String, String> map = new HashMap<>();
                map.put("Succes:", "true");
                map.put("Description:", "Your account is opened");
                map.put("Password:", newAcc.getPassword());
                repository.save(newAcc);
                return map;
            } else {
                HashMap<String, String> map = new HashMap<>();
                map.put("Error:", "Sorry :(. Account with that username already exist. Try something else");
                System.out.print(repository.existsAccountByAccountId((account.getAccountId())));
                return map;
            }
    }
}

