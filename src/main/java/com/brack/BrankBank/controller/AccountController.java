package com.brack.BrankBank.controller;

import com.brack.BrankBank.model.Accounts;
import com.brack.BrankBank.model.Users;
import com.brack.BrankBank.repositories.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    @Autowired
    private AccountsRepository accountsRepository;

    @GetMapping("/my-account")
    public Accounts getAccountDetails(@RequestParam String email){
    Accounts accounts = accountsRepository.findAccountsByCustomerEmail(email);
        if(accounts != null){
            return accounts;
        } else {
            return null;
        }
    }
}
