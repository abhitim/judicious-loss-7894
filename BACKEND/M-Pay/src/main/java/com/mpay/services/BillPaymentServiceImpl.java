package com.mpay.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpay.exceptions.InsufficientFundException;
import com.mpay.exceptions.CredentialsNotValidException;
import com.mpay.exceptions.WalletException;
import com.mpay.model.BillPayment;
import com.mpay.model.CurrentUserSession;
import com.mpay.model.Customer;
import com.mpay.model.Wallet;
import com.mpay.repositories.BillPaymentRepo;
import com.mpay.repositories.CustomerRepo;
import com.mpay.repositories.SessionRepo;
import com.mpay.repositories.WalletRepo;

@Service
public class BillPaymentServiceImpl implements BillPaymentService {

	@Autowired
	private BillPaymentRepo billRepo;

	@Autowired
	private WalletRepo walletRepo;

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private SessionRepo session;

	@Override
	public BillPayment addBillPayment(BillPayment bill, String key)
			throws CredentialsNotValidException, InsufficientFundException {
		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new CredentialsNotValidException("Invalid Session key.");
		Optional<Customer> customer = customerRepo.findById(currSession.getMobile());
		Optional<Wallet> wallet = walletRepo.findById(customer.get().getWallet().getWalletId());

		if (wallet.get().getBalance() < bill.getAmount())
			throw new InsufficientFundException("Insufficient Funds in Wallet.");

		wallet.get().setBalance(wallet.get().getBalance() - bill.getAmount());

		BillPayment savedbill = billRepo.save(bill);

		savedbill.setWallet(wallet.get());
		wallet.get().getBillPayments().add(bill);
		walletRepo.save(wallet.get());

		return savedbill;

	}

	@Override
	public List<BillPayment> viewAllBillPayments(String key) throws CredentialsNotValidException, WalletException {
		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new CredentialsNotValidException("Invalid Session key.");

		Optional<Customer> customer = customerRepo.findById(currSession.getMobile());
		Optional<Wallet> wallet = walletRepo.findById(customer.get().getWallet().getWalletId());

		if (!wallet.isPresent())
			throw new WalletException("Invalid User.");

		List<BillPayment> billList = wallet.get().getBillPayments();

		if (billList.size() == 0)
			throw new WalletException("No Bill Found.");

		return billList;
	}

}
