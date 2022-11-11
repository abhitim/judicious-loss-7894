package com.masai.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masai.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

	@Query("select new com.masai.model.Transaction(t.transactionId, t.transactionType, t.transactionDate, t.amount, t.description) from Transaction t where t.transactionDate BETWEEN ?1 and ?2")
	public List<Transaction> viewTransactionByDate(LocalDate from, LocalDate to);

	public List<Transaction> findByTypeAllTransaction(String type);
}
