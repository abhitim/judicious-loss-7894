package com.mpay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mpay.model.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

}
