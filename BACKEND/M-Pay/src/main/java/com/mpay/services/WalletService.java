package com.mpay.services;

import com.mpay.exceptions.CustomerException;
import com.mpay.exceptions.InsufficientFundException;
import com.mpay.exceptions.CredentialsNotValidException;
import com.mpay.exceptions.WalletException;
import com.mpay.model.BankAccount;
import com.mpay.model.Customer;
import com.mpay.model.Wallet;

public interface WalletService {

	public Wallet showBalance(String key) throws CustomerException, CredentialsNotValidException;

	public String fundTransfer(String targetMobileNo, double amount, String key)
			throws CustomerException, InsufficientFundException, CredentialsNotValidException;

	public Customer updateAccount(Customer customer, String key) throws CustomerException, CredentialsNotValidException;

	public Wallet addMoney(String key, double amount, BankAccount acc)
			throws WalletException, CredentialsNotValidException;
}
