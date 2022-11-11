package com.masai.service;



import javax.security.auth.login.AccountNotFoundException;

import com.masai.model.BankAccount;




public interface BankAccountService {

public String addAccount(BankAccount bankAccount,Integer walletId,String key);
	
	public BankAccount getAccountByAccountNumber(Integer accountNumber) throws com.masai.Exceptions.BankAccountNotFound;

	public String removeAccount(Integer accountNumber,String key)throws com.masai.Exceptions.BankAccountNotFound;
	
	
	
}
