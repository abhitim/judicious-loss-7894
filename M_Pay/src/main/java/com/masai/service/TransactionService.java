package com.masai.service;

import java.util.List;

import com.masai.Exceptions.InsufficientAmountException;
import com.masai.Exceptions.TransactionNotFoundException;
import com.masai.Exceptions.UserNotFoundException;
import com.masai.Exceptions.WalletNotFound;
import com.masai.model.Transaction;

public interface TransactionService {

	

	public Transaction addTansaction(Transaction trans) throws InsufficientAmountException;
	
	public List<Transaction> viewAllTransactions(String key,Integer walletId) throws TransactionNotFoundException, UserNotFoundException, WalletNotFound;
	
	public List<Transaction> viewTransactionByDate(Integer walletId, String date)throws TransactionNotFoundException, WalletNotFound;
	
	public List<Transaction> viewAllTransactions()throws TransactionNotFoundException;
}
