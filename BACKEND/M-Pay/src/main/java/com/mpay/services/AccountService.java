package com.mpay.services;

import java.util.List;

import com.mpay.exceptions.BankAccountException;
import com.mpay.exceptions.CredentialsNotValidException;
import com.mpay.exceptions.WalletException;
import com.mpay.model.BankAccount;
import com.mpay.model.Wallet;

public interface AccountService {

	public Wallet addAccount(BankAccount account, String key) throws BankAccountException, CredentialsNotValidException;

	public Wallet removeAccount(Integer accountNo, String key)
			throws BankAccountException, WalletException, CredentialsNotValidException;

	public List<BankAccount> viewAccounts(String key) throws BankAccountException, CredentialsNotValidException;

}
