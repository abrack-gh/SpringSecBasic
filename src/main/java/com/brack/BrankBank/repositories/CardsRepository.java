package com.brack.BrankBank.repositories;

import com.brack.BrankBank.model.Cards;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardsRepository extends CrudRepository<Cards, Long> {

    List<Cards> getCardsByCustomerId(int customerId);

}
