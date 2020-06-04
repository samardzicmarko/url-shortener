package com.rest.url_shortener.controllers;

import com.rest.url_shortener.model.Account;
import com.rest.url_shortener.repository.AccountRepository;

import com.rest.url_shortener.services.GenerateRandomString;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AccountController {

    private final AccountRepository repository;

    AccountController(AccountRepository repository){
        this.repository = repository;
    }

    @PostMapping(value = "/account", consumes = "application/json", produces = "application/json")
    public Map<String, String> addAccount(@RequestBody  Account account, HttpServletResponse response){

        if (account.getAccountId() == null){
            HashMap<String, String> map = new HashMap<>();
            map.put("Error:", "AccountId is required");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return map;
        }
        if (!repository.existsAccountByAccountId(account.getAccountId())) {

                Account newAcc = new Account();
                newAcc.setAccountId(account.getAccountId());
                newAcc.setPassword(GenerateRandomString.make(8));
                HashMap<String, String> map = new HashMap<>();
                map.put("Succes:", "true");
                map.put("Description:", "Your account is opened");
                map.put("Password:", newAcc.getPassword());
                response.setStatus(HttpServletResponse.SC_CREATED);
                repository.save(newAcc);
                return map;
            } else {
                HashMap<String, String> map = new HashMap<>();
                response.setStatus(HttpServletResponse.SC_FOUND);
                map.put("Error:", "Sorry :(. Account with that username already exist. Try something else");
                return map;
            }
    }
}

