package com.brack.BrankBank.controller;

import com.brack.BrankBank.model.Accounts;
import com.brack.BrankBank.repositories.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private AccountsRepository accountsRepository;

    @GetMapping("/my-account")
    public Accounts getAccountDetails(@RequestParam int id){
    Accounts accounts = accountsRepository.findAccountsByCustomerId(id);
        if(accounts != null){
            return accounts;
        } else {
            return null;
        }
    }
}
