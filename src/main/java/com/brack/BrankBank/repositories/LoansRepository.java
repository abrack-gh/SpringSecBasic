package com.brack.BrankBank.repositories;

import com.brack.BrankBank.model.Loans;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoansRepository extends CrudRepository<Loans, Long> {

//    @PreAuthorize("hasRole('USER')")
    List<Loans> findByCustomerIdOrderByStartDate(int customerId);

}
