package com.masai.service;

import java.time.LocalDate;
import java.util.List;

import com.masai.Exceptions.IllegalTransactionException;
import com.masai.dao.TransactionRepository;
import com.masai.model.Transaction;
import com.masai.model.Wallet;

public class TransactionServiceImpl implements TransactionService {

	private TransactionRepository tRepo;

	@Override
	public Transaction addTransaction(Transaction transaction) throws IllegalTransactionException {
		Transaction tran = tRepo.save(transaction);
		if (tran == null) {
			throw new IllegalTransactionException("Transaction didn't saved");
		} else {
			return tran;
		}

	}

	@Override
	public List<Transaction> viewTransactionByDate(LocalDate from, LocalDate to) {
		List<Transaction> transactions = tRepo.viewTransactionByDate(from, to);

		if (transactions.size() == 0) {
			
		}

		return transactions;
	}

	@Override
	public List<Transaction> viewAllTransaction(String type) {
		List<Transaction> transactions = tRepo.findByTypeAllTransaction(type);

		return transactions;
	}

	@Override
	public List<Transaction> viewAllTransaction(Wallet wallet) {
		
	}

}
