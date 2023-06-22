package com.brack.BrankBank.controller;

import com.brack.BrankBank.model.AccountTransactions;
import com.brack.BrankBank.repositories.AccountTransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BalanceController {

    @Autowired
    private AccountTransactionsRepository accountTransactionsRepository;

    @GetMapping("/my-balance")
    public List<AccountTransactions> getBalance(@RequestParam int id){
        List<AccountTransactions> accountTransactions = accountTransactionsRepository.
                findByCustomerIdOrderByTransactionDt(id);
        if(accountTransactions != null){
            return accountTransactions;
        } else {
            return null;
        }

    }

}
