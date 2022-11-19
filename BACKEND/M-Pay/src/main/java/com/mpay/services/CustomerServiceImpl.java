package com.mpay.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpay.exceptions.CustomerException;
import com.mpay.model.Customer;
import com.mpay.model.Wallet;
import com.mpay.repositories.CustomerRepo;
import com.mpay.repositories.SessionRepo;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepo cRepo;
	@Autowired
	private SessionRepo sRepo;

	@Override
	public Customer RegisterCustomer(Customer customer) throws CustomerException {
		Customer registerdCustomer = cRepo.findByMobile(customer.getMobile());

		if (registerdCustomer != null) {
			throw new CustomerException("Customer already exist with this mobile number");
		} else {
			Wallet wallet = new Wallet(0);
			wallet.setCustomer(customer);
			customer.setWallet(wallet);
			return cRepo.save(customer);
		}
	}

}
