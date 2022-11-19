package com.mpay.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpay.exceptions.BankAccountException;
import com.mpay.exceptions.CredentialsNotValidException;
import com.mpay.exceptions.WalletException;
import com.mpay.model.BankAccount;
import com.mpay.model.CurrentUserSession;
import com.mpay.model.Customer;
import com.mpay.model.Wallet;
import com.mpay.repositories.BankAccountRepo;
import com.mpay.repositories.CustomerRepo;
import com.mpay.repositories.SessionRepo;
import com.mpay.repositories.WalletRepo;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private BankAccountRepo brepo;

	@Autowired
	private WalletRepo wRepo;

	@Autowired
	private SessionRepo session;

	@Autowired
	private CustomerRepo cRepo;

	@Override
	public Wallet addAccount(BankAccount account, String key) throws BankAccountException, CredentialsNotValidException {
		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new CredentialsNotValidException("Invalid Session key");

		Optional<Customer> customer = cRepo.findById(currSession.getMobile());
		Optional<Wallet> wallet = wRepo.findById(customer.get().getWallet().getWalletId());

		List<BankAccount> accList = wallet.get().getBankAccounts();

		boolean flag = false;

		for (BankAccount acc : accList) {
			if (acc.getAccountNo().equals(account.getAccountNo())) {
				flag = true;
				break;
			}
		}

		if (flag)
			throw new BankAccountException("Bank Has already been added.");

		account.setWallet(wallet.get());

		wallet.get().getBankAccounts().add(account);

		return wRepo.save(wallet.get());

	}

	@Override
	public Wallet removeAccount(Integer accountNo, String key)
			throws BankAccountException, WalletException, CredentialsNotValidException {

		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new CredentialsNotValidException("Invalid Session key");

		Optional<Customer> customer = cRepo.findById(currSession.getMobile());
		Optional<Wallet> wallet = wRepo.findById(customer.get().getWallet().getWalletId());

		if (!wallet.isPresent())
			throw new WalletException("Unknown User.");

		List<BankAccount> accList = wallet.get().getBankAccounts();

		boolean flag = false;

		for (BankAccount acc : accList) {
			if (acc.getAccountNo().equals(accountNo)) {
				flag = true;
				break;
			}
		}

		if (!flag)
			throw new BankAccountException("Provided Bank account no not linked to the wallet.");

		List<BankAccount> accounts = wallet.get().getBankAccounts();

		accounts.removeIf(ac -> ac.getAccountNo().equals(accountNo));
		Optional<BankAccount> a = brepo.findById(accountNo);
		a.get().setWallet(null);
		brepo.save(a.get());

		return wallet.get();
	}

	@Override
	public List<BankAccount> viewAccounts(String key) throws BankAccountException, CredentialsNotValidException {

		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new CredentialsNotValidException("Invalid Session key");

		Optional<Customer> customer = cRepo.findById(currSession.getMobile());
		Optional<Wallet> w = wRepo.findById(customer.get().getWallet().getWalletId());

		if (!w.isPresent()) {
			throw new BankAccountException("Invalid wallet Id.");
		}

		List<BankAccount> accList = w.get().getBankAccounts();

		if (accList.size() == 0)
			throw new BankAccountException("No Banks Found.");

		return accList;
	}

}
