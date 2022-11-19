package com.mpay.services;

import java.time.LocalDate;
import java.util.List;

import com.mpay.exceptions.CredentialsNotValidException;
import com.mpay.exceptions.TransactionException;
import com.mpay.exceptions.WalletException;
import com.mpay.model.Transaction;

public interface TransactionService {

	public Transaction addTransaction(Transaction tran);

	public List<Transaction> viewAllTransaction(String key) throws WalletException, CredentialsNotValidException;

	public List<Transaction> viewTransactionByDate(LocalDate from, LocalDate to, String key)
			throws WalletException, CredentialsNotValidException;

	public Transaction viewTransaction(String key, Integer transactionId)
			throws TransactionException, CredentialsNotValidException;

}
