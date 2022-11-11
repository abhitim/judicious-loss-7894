package com.masai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exceptions.CustomerException;
import com.masai.dao.CustomerRepository;
import com.masai.dao.WalletRepository;
import com.masai.dto.CustomerSignInDto;
import com.masai.model.Customer;
import com.masai.model.Wallet;

@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private CustomerRepository cuRepository;
	@Autowired
	private WalletRepository wrepo;

	@Override
	public Customer registerCustomer(Customer customer) throws CustomerException {
		Customer cus=cuRepository.findByMobile(customer.getMobile());
		if(cus!=null) {
			throw new CustomerException("Customer already registered with mobile number : "+customer.getMobile());
		}
		Wallet w=customer.getWallets();
		w.setCustomer(customer);
		wrepo.save(w);
		return cuRepository.save(customer);
	}
	
	@Override
	public Customer validateCustomer(CustomerSignInDto customer) throws CustomerException {
		Customer cus=cuRepository.findByMobile(customer.getMobile());
		if(cus==null) {
			throw new CustomerException("Customer is not registered..");
		}
		if(!cus.getCustomerPassword().equals(customer.getPassword())) {
			throw new CustomerException("Password is incorrect..");
		}
		return cus;
	}
}
