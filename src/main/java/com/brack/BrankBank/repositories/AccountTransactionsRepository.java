package com.brack.BrankBank.repositories;

import com.brack.BrankBank.model.AccountTransactions;
import com.brack.BrankBank.model.Accounts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountTransactionsRepository extends CrudRepository<AccountTransactions, Long> {

    List<AccountTransactions> findByCustomerIdOOrderByTransactionDt(int customerId);

}
