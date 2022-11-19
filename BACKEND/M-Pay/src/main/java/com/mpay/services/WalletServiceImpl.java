package com.mpay.services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpay.exceptions.CustomerException;
import com.mpay.exceptions.InsufficientFundException;
import com.mpay.exceptions.CredentialsNotValidException;
import com.mpay.exceptions.WalletException;
import com.mpay.model.BankAccount;
import com.mpay.model.CurrentUserSession;
import com.mpay.model.Customer;
import com.mpay.model.Transaction;
import com.mpay.model.Wallet;
import com.mpay.repositories.BankAccountRepo;
import com.mpay.repositories.CustomerRepo;
import com.mpay.repositories.SessionRepo;
import com.mpay.repositories.TransactionRepo;
import com.mpay.repositories.WalletRepo;

@Service
public class WalletServiceImpl implements WalletService {

	@Autowired
	private WalletRepo wRepo;

	@Autowired
	private CustomerRepo cRepo;

	@Autowired
	private BankAccountRepo bRepo;

	@Autowired
	private TransactionRepo tRepo;

	@Autowired
	private SessionRepo session;

	@Override
	public Wallet showBalance(String key) throws CustomerException, CredentialsNotValidException {

		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new CredentialsNotValidException("Invalid Session key.");
		Optional<Customer> customer = cRepo.findById(currSession.getMobile());
		Optional<Wallet> wallet = wRepo.findById(customer.get().getWallet().getWalletId());

		return wallet.get();

	}

	@Override
	public String fundTransfer(String targetMobileNo, double amount, String key)
			throws CustomerException, InsufficientFundException, CredentialsNotValidException {

		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new CredentialsNotValidException("Invalid Session key.");
		Optional<Customer> customer1 = cRepo.findById(currSession.getMobile());

		if (!customer1.isPresent()) {
			throw new CustomerException("Sender account is not found");
		}

		Optional<Customer> customer2 = cRepo.findById(targetMobileNo);

		if (!customer2.isPresent()) {
			throw new CustomerException("Receiver account is not found");
		}

		if (customer1.get().getWallet().getBalance() < amount)
			throw new InsufficientFundException("Insufficient Funds in your Wallet.");

		Transaction tran = new Transaction();
		tran.setAmount(amount);
		tran.setDescription("Funds Transfered to " + customer2.get().getName());
		tran.setTransactionDate(LocalDate.now());
		tran.setTransactionType("Fund Transfer");
		tran.setWallet(customer1.get().getWallet());

		tRepo.save(tran);

		Transaction tran2 = new Transaction();
		tran2.setAmount(amount);
		tran2.setDescription("Funds Received from " + customer1.get().getName());
		tran2.setTransactionDate(LocalDate.now());
		tran2.setTransactionType("Funds Transfer");
		tran2.setWallet(customer2.get().getWallet());

		tRepo.save(tran2);

		double updatedBalance1 = customer1.get().getWallet().getBalance() - amount;
		customer1.get().getWallet().setBalance(updatedBalance1);
		cRepo.save(customer1.get());

		double updatedBalance2 = customer2.get().getWallet().getBalance() + amount;
		customer2.get().getWallet().setBalance(updatedBalance2);
		cRepo.save(customer2.get());

		return "Fund is transfered successfully...";
	}

	@Override
	public Customer updateAccount(Customer customer, String key) throws CustomerException, CredentialsNotValidException {

		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new CredentialsNotValidException("Invalid Session key.");
		Optional<Customer> customer1 = cRepo.findById(currSession.getMobile());

		if (!customer1.isPresent())
			throw new CustomerException("Customer Not Found.");

		if (customer.getName() != null)
			customer1.get().setName(customer.getName());
		if (customer.getPassword() != null)
			customer1.get().setPassword(customer.getPassword());

		return cRepo.save(customer1.get());

	}

	@Override
	public Wallet addMoney(String key, double amount, BankAccount acc)
			throws WalletException, CredentialsNotValidException {

		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new CredentialsNotValidException("Invalid Session key.");
		Optional<Customer> customer = cRepo.findById(currSession.getMobile());
		Optional<Wallet> wallet1 = wRepo.findById(customer.get().getWallet().getWalletId());

		if (wallet1.isPresent()) {

			Optional<BankAccount> account = bRepo.findById(acc.getAccountNo());
			if (!account.isPresent() || account.get().getWallet().getWalletId() != wallet1.get().getWalletId())
				throw new WalletException("bankAccount Not Found.");

			Transaction tran = new Transaction();
			tran.setAmount(amount);
			tran.setDescription("Funds Added From Bank Account " + acc.getAccountNo());
			tran.setTransactionDate(LocalDate.now());
			tran.setTransactionType("Self Deposit.");
			tran.setWallet(wallet1.get());

			tRepo.save(tran);

			account.get().setBalance(account.get().getBalance() - amount);
			wallet1.get().setBalance(wallet1.get().getBalance() + amount);
//			wallet1.get().getTransactions().add(tran);

			bRepo.save(account.get());
			Wallet w = wRepo.save(wallet1.get());
			return w;
		} else {
			throw new WalletException("Wallet not found");
		}
	}

}
