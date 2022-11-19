package com.mpay.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpay.exceptions.BeneficiaryNotFoundException;
import com.mpay.exceptions.CustomerException;
import com.mpay.exceptions.CredentialsNotValidException;
import com.mpay.exceptions.WalletException;
import com.mpay.model.Beneficiary;
import com.mpay.model.CurrentUserSession;
import com.mpay.model.Customer;
import com.mpay.model.Wallet;
import com.mpay.repositories.BeneficiaryRepo;
import com.mpay.repositories.CustomerRepo;
import com.mpay.repositories.SessionRepo;
import com.mpay.repositories.WalletRepo;

@Service
public class BenificiaryServiceImpl implements BenificiaryService {

	@Autowired
	private BeneficiaryRepo bRepo;

	@Autowired
	private WalletRepo walletRepo;

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private SessionRepo session;

	@Override
	public Beneficiary addBeneficiary(Beneficiary bd, String key)
			throws CustomerException, WalletException, CredentialsNotValidException {
		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new CredentialsNotValidException("Invalid Session key.");
		Optional<Customer> customer = customerRepo.findById(currSession.getMobile());
		Optional<Wallet> wallet = walletRepo.findById(customer.get().getWallet().getWalletId());

		if (!customer.isPresent())
			throw new CustomerException("Beneficiary is not Registered to the Application.");

		if (!wallet.isPresent())
			throw new WalletException("Invalid User.");

		Optional<Beneficiary> ben = bRepo.findById(bd.getMobile());

		if (ben.isPresent()) {
			ben.get().getWalletList().add(wallet.get());
			wallet.get().getBeneficiaryList().add(ben.get());
			return bRepo.save(ben.get());
		} else {
			bd.getWalletList().add(wallet.get());
			return bRepo.save(bd);
		}
	}

	@Override
	public Beneficiary deleteBeneficiary(Beneficiary bd, String key)
			throws BeneficiaryNotFoundException, WalletException, CredentialsNotValidException {
		Optional<Beneficiary> ben = bRepo.findById(bd.getMobile());
		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new CredentialsNotValidException("Invalid Session key.");
		Optional<Customer> customer = customerRepo.findById(currSession.getMobile());
		Optional<Wallet> wallet = walletRepo.findById(customer.get().getWallet().getWalletId());

		if (!ben.isPresent())
			throw new BeneficiaryNotFoundException("Invalid Beneficiary Details.");

		if (!wallet.isPresent())
			throw new WalletException("Invalid User.");

		boolean flag = false;
		List<Beneficiary> benList = wallet.get().getBeneficiaryList();

		for (Beneficiary b : benList) {
			if (b.getMobile().equals(ben.get().getMobile())) {
				flag = true;
				break;
			}
		}

		if (!flag)
			throw new BeneficiaryNotFoundException("Beneficiary not linked with this account.");

		List<Wallet> walletList = ben.get().getWalletList();

		walletList.removeIf(w -> w.getWalletId().equals(wallet.get().getWalletId()));
		benList.removeIf(b -> b.getMobile().equals(bd.getMobile()));

		walletRepo.save(wallet.get());
		return bRepo.save(ben.get());
	}

	@Override
	public Beneficiary viewBeneficiary(String mobile, String key)
			throws BeneficiaryNotFoundException, CredentialsNotValidException {

		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new CredentialsNotValidException("Invalid Session key.");
		Optional<Customer> customer = customerRepo.findById(currSession.getMobile());
		Optional<Wallet> wallet = walletRepo.findById(customer.get().getWallet().getWalletId());
		List<Beneficiary> benList = wallet.get().getBeneficiaryList();

		for (Beneficiary ben : benList) {
			if (ben.getMobile().equals(mobile)) {
				return ben;
			}
		}

		throw new BeneficiaryNotFoundException("Beneficiary not found");
	}

	@Override
	public List<Beneficiary> viewAllBeneficiary(String key)
			throws BeneficiaryNotFoundException, CredentialsNotValidException {
		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new CredentialsNotValidException("Invalid Session key.");
		Optional<Customer> customer = customerRepo.findById(currSession.getMobile());
		Optional<Wallet> wallet = walletRepo.findById(customer.get().getWallet().getWalletId());
		List<Beneficiary> blist = wallet.get().getBeneficiaryList();

		if (blist.size() != 0)
			return blist;
		else
			throw new BeneficiaryNotFoundException("No Beneficiaries Found.");
	}

}
