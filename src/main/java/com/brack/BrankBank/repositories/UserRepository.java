package com.brack.BrankBank.repositories;

import com.brack.BrankBank.model.Users;
import jakarta.persistence.Id;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {

    List<Users> findByEmail(String email);

}
