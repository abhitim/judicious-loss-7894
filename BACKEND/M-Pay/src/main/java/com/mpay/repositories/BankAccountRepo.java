package com.mpay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mpay.model.BankAccount;

@Repository
public interface BankAccountRepo extends JpaRepository<BankAccount, Integer> {

}
