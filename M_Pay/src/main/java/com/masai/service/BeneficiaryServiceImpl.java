package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exceptions.BeneficiaryException;
import com.masai.dao.BeneficiaryRepository;
import com.masai.dao.CustomerRepository;
import com.masai.dao.WalletDao;
import com.masai.model.BeneficiaryDetails;
import com.masai.model.Customer;
import com.masai.model.Wallet;


@Service
public class BeneficiaryServiceImpl implements BeneficiaryService{
	@Autowired
	private BeneficiaryRepository repository;
	@Autowired
	private WalletDao repo;
	@Autowired
	private CustomerRepository crepo;

	@Override
	public BeneficiaryDetails addBeneficiary(BeneficiaryDetails beDetails) throws BeneficiaryException {
		BeneficiaryDetails beneDetails=repository.getByMobile(beDetails.getMobileNumber());
		
		if(beneDetails!=null) {
			throw new BeneficiaryException("Beneficiary is already registered..");
		}
		
		Wallet w=beDetails.getWallet();
		w.getBd().add(beneDetails);
		repo.save(w);
		
		return repository.save(beDetails);
		
		
	}

	@Override
	public BeneficiaryDetails deleteBeneficiary(BeneficiaryDetails beDetails) throws BeneficiaryException {
		BeneficiaryDetails be=repository.getByMobile(beDetails.getMobileNumber());
		if(be==null) {
			throw new BeneficiaryException("Beneficiary not exist with mobile number : "+beDetails.getMobileNumber());
		}
		repository.delete(be);
		return be;
	}

	@Override
	public BeneficiaryDetails veiwBeneficiary(String mobileNo) throws BeneficiaryException {
		BeneficiaryDetails be=repository.getByMobile(mobileNo);
		if(be==null) {
			throw new BeneficiaryException("Beneficiary not exist with mobile number : "+mobileNo);
		}
		return be;
	}

	@Override
	public List<BeneficiaryDetails> veiwAllBeneficiary(Customer customer) throws BeneficiaryException {
		Customer cus=crepo.findByMobile(customer.getMobile());
		if(cus==null) {
			throw new BeneficiaryException("Beneficiary not exist..");
		}
		Wallet w=cus.getWallets();
		List<BeneficiaryDetails> benelist= w.getBd();
		
		if(benelist.isEmpty()) {
			throw new BeneficiaryException("Beneficiary list is empty..");
		}
		return benelist;
	}
	
}
