package com.masai.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
