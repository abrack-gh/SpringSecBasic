package com.brack.BrankBank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardsController {

    @GetMapping("/my-cards")
    public String getCards(){
        return "Card details";
    }
}
