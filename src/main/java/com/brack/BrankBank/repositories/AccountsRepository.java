package com.brack.BrankBank.repositories;

import com.brack.BrankBank.model.Accounts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends CrudRepository<Accounts, Long> {

    Accounts findAccountsByCustomerId(int customerId);
}
