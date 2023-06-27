package com.brack.BrankBank.controller;

import com.brack.BrankBank.model.Loans;
import com.brack.BrankBank.model.Users;
import com.brack.BrankBank.repositories.LoansRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Loans> getLoanDetails(@RequestParam Integer id){
        List<Loans> loans = loansRepository.getLoansByCustomerIdOrderByStartDate(id);
        if(loans != null){
            return loans;
        } else {
            return null;
        }
    }



}
