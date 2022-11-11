package com.masai.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.masai.Exceptions.IllegalTransactionException;
import com.masai.model.Transaction;
import com.masai.model.Wallet;

@Service
public interface TransactionService {

	public Transaction addTransaction(Transaction transaction) throws IllegalTransactionException;

	public List<Transaction> viewTransactionByDate(LocalDate from, LocalDate to);
	
	public List<Transaction> viewAllTransaction(Wallet wallet);

	public List<Transaction> viewAllTransaction(String type);

}
