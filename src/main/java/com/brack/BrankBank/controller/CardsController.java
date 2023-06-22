package com.brack.BrankBank.controller;

import com.brack.BrankBank.model.Cards;
import com.brack.BrankBank.repositories.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardsController {

    @Autowired
    private CardsRepository cardsRepository;

    @GetMapping("/my-cards")
    public List<Cards> getCards(@RequestParam int id){
        List<Cards> cards = cardsRepository.listCardsByCustomerId(id);
        if(cards != null){
            return cards;
        } else {
            return null;
        }
    }
}
