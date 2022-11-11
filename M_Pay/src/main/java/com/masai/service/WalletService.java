package com.masai.service;



import com.masai.Exceptions.BankAccountNotFound;
import com.masai.Exceptions.InsufficientAmountException;
import com.masai.Exceptions.InvalidAccountException;
import com.masai.model.Customer;

public interface WalletService {

	
	public Integer showBalance(String key) throws InvalidAccountException;
	

	
	public String depositAmount(Integer amount, String key, Integer Accno) throws BankAccountNotFound, InsufficientAmountException;
	
	public String addMoney(Integer amount, String key,Integer Accno) throws BankAccountNotFound, InsufficientAmountException;
	

	
	public Customer getCustomerbyWalletId(Integer walletId);
	
}
