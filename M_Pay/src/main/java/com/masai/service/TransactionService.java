package com.masai.service;

import java.time.LocalDate;
import java.util.List;

import com.masai.model.Transaction;

public interface TransactionService {

	public Transaction addTransaction(Transaction transaction);

	public List<Transaction> viewTransactionByDate(LocalDate from, LocalDate to);

	public List<Transaction> viewAllTransaction(String type);

}
