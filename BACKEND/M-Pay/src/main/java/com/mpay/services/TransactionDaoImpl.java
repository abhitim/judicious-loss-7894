package com.mpay.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpay.exceptions.CredentialsNotValidException;
import com.mpay.exceptions.TransactionException;
import com.mpay.exceptions.WalletException;
import com.mpay.model.CurrentUserSession;
import com.mpay.model.Customer;
import com.mpay.model.Transaction;
import com.mpay.model.Wallet;
import com.mpay.repositories.CustomerRepo;
import com.mpay.repositories.SessionRepo;
import com.mpay.repositories.TransactionRepo;
import com.mpay.repositories.WalletRepo;

@Service
public class TransactionDaoImpl implements TransactionService {

	@Autowired
	private TransactionRepo tRepo;

	@Autowired
	private WalletRepo wRepo;

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private SessionRepo session;

	@Override
	public Transaction addTransaction(Transaction tran) {
		Transaction transaction = tRepo.save(tran);
		return transaction;
	}

	@Override
	public List<Transaction> viewAllTransaction(String key) throws WalletException, CredentialsNotValidException {

		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new CredentialsNotValidException("Invalid Session key.");
		Optional<Customer> customer = customerRepo.findById(currSession.getMobile());
		Optional<Wallet> wal = wRepo.findById(customer.get().getWallet().getWalletId());

		if (!wal.isPresent())
			throw new WalletException("Invalid Wallet Details.");

		return wal.get().getTransactions();
	}

	@Override
	public List<Transaction> viewTransactionByDate(LocalDate from, LocalDate to, String key)
			throws WalletException, CredentialsNotValidException {
		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new CredentialsNotValidException("Invalid Session key.");
		Optional<Customer> customer = customerRepo.findById(currSession.getMobile());
		Optional<Wallet> wallet = wRepo.findById(customer.get().getWallet().getWalletId());

		if (!wallet.isPresent())
			throw new WalletException("Invalid User.");

		List<Transaction> tranList = wallet.get().getTransactions();
		List<Transaction> filtered = new ArrayList<>();
		for (Transaction t : tranList) {
			if ((t.getTransactionDate().isAfter(from) && t.getTransactionDate().isBefore(to))
					|| t.getTransactionDate().equals(from) || t.getTransactionDate().equals(to)) {
				filtered.add(t);
			}
		}

		if (filtered.size() == 0)
			throw new WalletException("No Transactions Found.");

		return filtered;
	}

	@Override
	public Transaction viewTransaction(String key, Integer transactionId)
			throws TransactionException, CredentialsNotValidException {

		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new CredentialsNotValidException("Invalid Session key.");
		Optional<Customer> customer = customerRepo.findById(currSession.getMobile());
		Optional<Wallet> wallet = wRepo.findById(customer.get().getWallet().getWalletId());
		Optional<Transaction> tran = tRepo.findById(transactionId);

		if (!tran.isPresent())
			throw new TransactionException("Invalid transaction id");
		if (tran.get().getWallet().getWalletId() != wallet.get().getWalletId())
			throw new TransactionException("Transaction not Found");

		return tran.get();
	}

}
