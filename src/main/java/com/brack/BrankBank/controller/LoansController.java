package com.brack.BrankBank.controller;

import com.brack.BrankBank.model.Loans;
import com.brack.BrankBank.model.Users;
import com.brack.BrankBank.repositories.LoansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoansController {

    @Autowired
    private LoansRepository loansRepository;

    @GetMapping("/my-loans")
    @PostAuthorize("hasRole('USER')") //method level security
    public List<Loans> getLoanDetails(@RequestParam String email){
        List<Loans> loans = loansRepository.findByCustomerEmailOrderByStartDate(email);
        if(loans != null){
            return loans;
        } else {
            return null;
        }
    }



}
